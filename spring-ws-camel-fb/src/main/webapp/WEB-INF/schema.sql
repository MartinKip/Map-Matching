drop table if exists `users`;
CREATE TABLE `users` (
	`ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`FacebookId` INT(20) NOT NULL,
	`TwitterName` VARCHAR(256) NOT NULL,
	PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

drop table if exists `matches`;
CREATE TABLE `matches` (
	`ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	`UserId1` INT(10) NOT NULL,
	`UserId2` INT(10) NOT NULL,
	`match` INT(10) NOT NULL,
    PRIMARY KEY (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
