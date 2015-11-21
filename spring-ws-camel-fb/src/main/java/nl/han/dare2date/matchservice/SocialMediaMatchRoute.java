package nl.han.dare2date.matchservice;

import nl.han.dare2date.matchservice.model.MatchResponse;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.apache.camel.component.twitter.TwitterConstants;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

public class SocialMediaMatchRoute extends RouteBuilder {
    private int profileID = 0;


    @Override
    public void configure() throws Exception {
        JaxbDataFormat jaxbMatchResponse = new JaxbDataFormat(MatchResponse.class.getPackage().getName());
        Namespaces ns = new Namespaces("mes", "http://www.han.nl/schemas/messages");

        profileID = profileID + 1;

        from("spring-ws:rootqname:{http://www.han.nl/schemas/messages}MatchRequest?endpointMapping=#matchEndpointMapping")
            .setExchangePattern(ExchangePattern.InOut)
            .split(ns.xpath("//mes:user/*"), new SocialMediaMatchAggregrate()) // split the request into four separate parts
                .parallelProcessing() // fork
                .convertBodyTo(String.class) // we'll handle with the request as XML using XPath
                .choice()
                    .when(body().contains("twitterName")) // send the twitterName to twitter and wait for the aggregrate to do something useful
                    .setHeader("profileID", constant(profileID))
                    .setHeader(TwitterConstants.TWITTER_KEYWORDS, ns.xpath("/mes:twitterName/text()", String.class)) // fill the keyword parameter
                    .to("twitter://search")
                .otherwise() // send the facebookid to FB and wait for the aggregrate to do something useful
                    .when(body().contains("facebookid"))
                    .setHeader("profileID", constant(profileID))
                    .setHeader("CamelFacebook.userId", ns.xpath("/mes:facebookid/text()", String.class)) // fill the userid parameter
                    .to("facebook://user")
                .end() // end the parallel processing, this is a kind of "join"
            .end() // stop splitting and start returnin
                .to("stream:out")
                .setBody().body()
                .to("smtps://smtp.gmail.com:465?username=map.matchingcamel@gmail.com&password=mapmatchingwachtwoord&subject=Match Made!")
        .marshal(jaxbMatchResponse); // serialize the java-object from the aggregrator to SOAP/XML

    }
}
