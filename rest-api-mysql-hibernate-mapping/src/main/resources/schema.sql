CREATE SCHEMA IF NOT EXISTS `user-app` ;

CREATE TABLE IF NOT EXISTS `CITY` (
  `CODE` VARCHAR(3) NOT NULL,
  `NAME` VARCHAR(30) NOT NULL,
  `DESCRIPTION` VARCHAR(255) DEFAULT NULL,
  `STATUS` VARCHAR(20) NOT NULL,
  `CREATED_BY` VARCHAR(30) NOT NULL,
  `CREATED_DATE` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` VARCHAR(255) DEFAULT NULL,
  `UPDATED_DATE` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI;

CREATE TABLE IF NOT EXISTS `STATE` (
  `CODE` VARCHAR(3) NOT NULL,
  `NAME` VARCHAR(30) NOT NULL,
  `DESCRIPTION` VARCHAR(255) DEFAULT NULL,
  `STATUS` VARCHAR(20) NOT NULL,
  `CREATED_BY` VARCHAR(30) NOT NULL,
  `CREATED_DATE` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` VARCHAR(30) DEFAULT NULL,
  `UPDATED_DATE` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI;

CREATE TABLE IF NOT EXISTS `GENDER` (
  `CODE` VARCHAR(10) NOT NULL,
  `NAME` VARCHAR(20) NOT NULL,
  `DESCRIPTION` VARCHAR(255) DEFAULT NULL,
  `STATUS` VARCHAR(20) NOT NULL,
  `CREATED_BY` VARCHAR(30) NOT NULL,
  `CREATED_DATE` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` VARCHAR(30) DEFAULT NULL,
  `UPDATED_DATE` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI;

CREATE TABLE IF NOT EXISTS `USER` (
  `USER_ID` VARCHAR(30) NOT NULL,
  `USER_NAME` VARCHAR(20) NOT NULL,
  `PASSWORD` VARCHAR(512) NOT NULL,
  `FIRST_NAME` VARCHAR(100) NOT NULL,
  `LAST_NAME` VARCHAR(100) NOT NULL,
  `EMAIL` VARCHAR(512) NOT NULL,
  `GENDER` VARCHAR(10) NOT NULL,
  `DOB` TIMESTAMP DEFAULT NULL,
  `CREATED_BY` VARCHAR(30) NOT NULL,
  `CREATED_DATE` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` VARCHAR(30) DEFAULT NULL,
  `UPDATED_DATE` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `UK_USER_EMAIL` (`EMAIL`),
  UNIQUE KEY `UK_USER_NAME` (`USER_NAME`),
  CONSTRAINT `FK_USER_GENDER` FOREIGN KEY (`GENDER`) REFERENCES `GENDER` (`CODE`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI;

CREATE TABLE IF NOT EXISTS `ADDRESS_DETAILS` (
  `ADDRESS_ID` INT NOT NULL AUTO_INCREMENT,
  `ADDRESS_TYPE` VARCHAR(10) NOT NULL,
  `USER_ID` VARCHAR(30) NOT NULL,
  `ADDRESS_LINE_01` VARCHAR(100) NOT NULL,
  `ADDRESS_LINE_02` VARCHAR(100) DEFAULT NULL,
  `ADDRESS_LINE_03` VARCHAR(100) DEFAULT NULL,
  `LAND_MARK` VARCHAR(100) DEFAULT NULL,
  `PIN_CODE` VARCHAR(10) NOT NULL,
  `PRIMARY_CONTACT_NUMBER` VARCHAR(12) DEFAULT NULL,
  `SECONDARY_CONTACT_NUMBER` VARCHAR(12) DEFAULT NULL,
  `CITY` VARCHAR(3) DEFAULT NULL,
  `STATE` VARCHAR(3) DEFAULT NULL,
  `STATUS` VARCHAR(20) DEFAULT NULL,
  `CREATED_BY` VARCHAR(30) NOT NULL,
  `CREATED_DATE` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` VARCHAR(30) DEFAULT NULL,
  `UPDATED_DATE` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`ADDRESS_ID`),
  UNIQUE KEY `ADDRESSTYPEANDUSER` (`ADDRESS_TYPE`,`USER_ID`),
  KEY `FK_ADDRESS_DETAILS_CITY` (`CITY`),
  KEY `FK_ADDRESS_DETAILS_STATE` (`STATE`),
  KEY `FK_ADDRESS_DETAILS_STATE_USER_ID` (`USER_ID`),
  CONSTRAINT `FK_ADDRESS_DETAILS_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`USER_ID`),
  CONSTRAINT `FK_ADDRESS_DETAILS_STATE` FOREIGN KEY (`STATE`) REFERENCES `STATE` (`CODE`),
  CONSTRAINT `FK_ADDRESS_DETAILS_CITY` FOREIGN KEY (`CITY`) REFERENCES `CITY` (`CODE`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI;

CREATE TABLE  IF NOT EXISTS `ROLE` (
  `ROLE_ID` INT NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` VARCHAR(30) DEFAULT NULL,
  `STATUS` VARCHAR(20) DEFAULT NULL,
  `CREATED_BY` VARCHAR(30) NOT NULL,
  `CREATED_DATE` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` VARCHAR(30) DEFAULT NULL,
  `UPDATED_DATE` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI;

CREATE TABLE IF NOT EXISTS `USER_ROLE` (
  `USER_ID` VARCHAR(30) NOT NULL,
  `ROLE_ID` INT NOT NULL,
  KEY `FK_USER_ROLE_ROLE_ID` (`ROLE_ID`),
  KEY `FK_USER_ROLE_USER_ID` (`USER_ID`),
  CONSTRAINT `FK_USER_ROLE_ROLE_ID` FOREIGN KEY (`USER_ID`) REFERENCES `USER` (`USER_ID`),
  CONSTRAINT `FK_USER_ROLE_USER_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `ROLE` (`ROLE_ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI;

CREATE TABLE IF NOT EXISTS `POST` (
  `POST_ID` INT NOT NULL AUTO_INCREMENT,
  `POST_TYPE` VARCHAR(20) DEFAULT NULL,
  `POST_DESCRIPTION` VARCHAR(512) DEFAULT NULL,
  `IMAGE` LONGTEXT,
  `IMAGE_NAME` VARCHAR(255) DEFAULT NULL,
  `POSTED_BY` VARCHAR(30) DEFAULT NULL,
  `STATUS` VARCHAR(20) DEFAULT NULL,
  `CREATED_BY` VARCHAR(30) NOT NULL,
  `CREATED_DATE` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` VARCHAR(30) DEFAULT NULL,
  `UPDATED_DATE` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`POST_ID`),
  UNIQUE KEY `UK_POST_POSTED_BY` (`POSTED_BY`),
  CONSTRAINT `FK_POST_POSTED_BY` FOREIGN KEY (`POSTED_BY`) REFERENCES `USER` (`USER_ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI;

CREATE TABLE IF NOT EXISTS `COMMENTS` (
  `COMMENT_ID` INT NOT NULL AUTO_INCREMENT,
  `POST_ID` INT NOT NULL,
  `COMMENTED_BY` VARCHAR(30) NOT NULL,
  `COMMENT_DESCRIPTION` VARCHAR(512) NOT NULL,
  `CREATED_BY` VARCHAR(30) NOT NULL,
  `CREATED_DATE` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` VARCHAR(30) DEFAULT NULL,
  `UPDATED_DATE` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`COMMENT_ID`),
  UNIQUE KEY `UK_COMMENTS_COMMENTED_BY` (`COMMENTED_BY`),
  KEY `FK_COMMENTS_POST_ID` (`POST_ID`),
  CONSTRAINT `FK_COMMENTS_POST_ID` FOREIGN KEY (`POST_ID`) REFERENCES `POST` (`POST_ID`),
  CONSTRAINT `FK_COMMENTS_COMMENTED_BY` FOREIGN KEY (`COMMENTED_BY`) REFERENCES `USER` (`USER_ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_0900_AI_CI;

-- Sequence generator
CREATE TABLE IF NOT EXISTS USER_ID_SEQ (NEXT_VAL BIGINT);




