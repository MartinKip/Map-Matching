drop table if exists `users`;
CREATE TABLE `users` (
	`ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`MatchValue` VARCHAR(255) NOT NULL,
	PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
