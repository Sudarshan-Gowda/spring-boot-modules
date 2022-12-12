# Rest API with Spring Boot, Hibernate, JPA

This repo covers
  * Rest API using Spring boot & MySQL
  * Hibernate Mapping [One to One, One To Many, Many To One, Many To Many]
  * Schema and Master data scripts
  * Global Exception handling

### Technology Used:     
  * Java 8                                                                                                                                
  * Spring Boot & Spring Framework                                                                                                                    
  * JPA & Hibernate
  * Junit and Mockito
  * Sonarqube                                                                                                                  
                                                                                                                           
 
### Rest API Curl Commands

#### Create User Record:

```
curl --location --request POST 'http://localhost:8080/mapping/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userName": "sudarshan",
    "email": "sudarshan@gmail.com",
    "password": "password",
    "firstName": "Sudarshan",
    "lastName": "NP",
    "gender": "MALE",
    "dob": "1990-01-01",
    "roles": [
        {
            "roleId": 1
        }
    ],
    "addressDetails": [
        {
            "addressType": "HOME",
            "addressLine01": "01, Park square",
            "addressLine02": "Whitefield",
            "addressLine03": "Bangalore",
            "pinCode": "560066",
            "state": "KA",
            "city": "IND",
            "landMark": "Next to Govn School",
            "primaryContactNumber": "10000000000",
            "secondaryContactNumber": "20000000000"
        }
    ]
}'
```


#### Get User Records:

```
curl --location --request GET 'http://localhost:8080/mapping/users' \
--data-raw ''
```

#### User Record By Id:

```
curl --location --request GET 'http://localhost:8080/mapping/users/USER00001'
```

#### Update User Record

```
curl --location --request PUT 'http://localhost:8080/mapping/users/USER00001' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userName": "sudarshan",
    "email": "sudarshan@gmail.com",
    "password": "password01",
    "firstName": "Test 01",
    "lastName": "01",
    "gender": "MALE",
    "dob": "1990-01-01",
    "roles": [
        {
            "roleId": 2
        }
    ],
    "addressDetails": [
        {
            "addressType": "OFFICE",
            "addressLine01": "01, Park square",
            "addressLine02": "Whitefield",
            "addressLine03": "Bangalore",
            "pinCode": "560066",
            "state": "KA",
            "city": "IND",
            "landMark": "Next to Govn School",
            "primaryContactNumber": "10000000000",
            "secondaryContactNumber": "20000000000"
        }
    ]
}'
```

#### Delete User Record

```
curl --location --request DELETE 'http://localhost:8080/mapping/users/USER00001'
```

### SQL Commands

#### Create Scripts
```
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
  UNIQUE KEY `UK_USER_GENDER` (`GENDER`),
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

```

#### Insert Scripts:
```
-- ROLE
INSERT INTO ROLE (ROLE_NAME,STATUS,CREATED_BY)
SELECT 'ADMIN','ACTIVE','SYSTEM' WHERE NOT EXISTS (SELECT 1 FROM ROLE WHERE ROLE_NAME = 'ADMIN' AND STATUS = 'ACTIVE');

INSERT INTO ROLE (ROLE_NAME,STATUS,CREATED_BY)
SELECT 'USER','ACTIVE','SYSTEM' WHERE NOT EXISTS (SELECT 1 FROM ROLE WHERE ROLE_NAME = 'USER' AND STATUS = 'ACTIVE');

-- GENDER
INSERT INTO GENDER (CODE,NAME,STATUS,CREATED_BY)
SELECT 'MALE','Male','ACTIVE','ADMIN' WHERE NOT EXISTS (SELECT 1 FROM GENDER WHERE CODE = 'MALE' AND STATUS = 'ACTIVE');

INSERT INTO GENDER (CODE,NAME,STATUS,CREATED_BY)
SELECT 'FEMALE','Female','ACTIVE','ADMIN' WHERE NOT EXISTS (SELECT 1 FROM GENDER WHERE CODE = 'FEMALE' AND STATUS = 'ACTIVE');

INSERT INTO GENDER (CODE,NAME,STATUS,CREATED_BY)
SELECT 'OTHER','Other','ACTIVE','ADMIN' WHERE NOT EXISTS (SELECT 1 FROM GENDER WHERE CODE = 'OTHER' AND STATUS = 'ACTIVE');

-- CITY
INSERT INTO CITY (CODE,NAME,STATUS,CREATED_BY)
SELECT 'IND','India','ACTIVE','ADMIN' WHERE NOT EXISTS (SELECT 1 FROM CITY WHERE CODE = 'IND' AND STATUS = 'ACTIVE');

-- STATE
INSERT INTO STATE (CODE,NAME,STATUS,CREATED_BY)
SELECT 'KA','Karnataka','ACTIVE','ADMIN' WHERE NOT EXISTS (SELECT 1 FROM STATE WHERE CODE = 'KA' AND STATUS = 'ACTIVE');

INSERT INTO STATE (CODE,NAME,STATUS,CREATED_BY)
SELECT 'AD','Andhra Pradesh','ACTIVE','ADMIN' WHERE NOT EXISTS (SELECT 1 FROM STATE WHERE CODE = 'AD' AND STATUS = 'ACTIVE');

INSERT INTO STATE (CODE,NAME,STATUS,CREATED_BY)
SELECT 'MP','Madhya Pradesh','ACTIVE','ADMIN' WHERE NOT EXISTS (SELECT 1 FROM STATE WHERE CODE = 'MP' AND STATUS = 'ACTIVE');

-- USER_ID_SEQ
INSERT INTO USER_ID_SEQ (NEXT_VAL)
SELECT 1 WHERE NOT EXISTS (SELECT 1 FROM USER_ID_SEQ WHERE NEXT_VAL IS NOT NULL);

```


### Rest API with JSON Collection

#### Create User Record: [`http://localhost:8080/mapping/users`]

##### Request:
```
{
    "userName": "sudarshan",
    "email": "sudarshan@gmail.com",
    "password": "password",
    "firstName": "Sudarshan",
    "lastName": "NP",
    "gender": "MALE",
    "dob": "1990-01-01",
    "roles": [
        {
            "roleId": 1
        }
    ],
    "addressDetails": [
        {
            "addressType": "HOME",
            "addressLine01": "01, Park square",
            "addressLine02": "Whitefield",
            "addressLine03": "Bangalore",
            "pinCode": "560066",
            "state": "KA",
            "city": "IND",
            "landMark": "Next to Govn School",
            "primaryContactNumber": "10000000000",
            "secondaryContactNumber": "20000000000"
        }
    ]
}
```
##### Response:
```
{
    "data": {
        "userId": "USER00001",
        "userName": "sudarshan",
        "email": "test@gmail.com",
        "firstName": "Test",
        "lastName": "01",
        "dob": "1990-01-01",
        "addressDetails": [
            {
                "addressId": 3,
                "addressType": "HOME",
                "addressLine01": "01, Park square",
                "addressLine02": "Whitefield",
                "addressLine03": "Bangalore",
                "pinCode": "560066",
                "landMark": "Next to Govn School",
                "primaryContactNumber": "10000000000",
                "secondaryContactNumber": "20000000000",
                "createdBy": "ADMIN",
                "createdDate": "2022-12-12T08:45:52.271+00:00",
                "updatedDate": "2022-12-12T08:45:52.271+00:00"
            }
        ],
        "roles": [
            {
                "roleId": 2
            }
        ]
    }
}
```

#### Get User Records: [`http://localhost:8080/mapping/users`]

##### Response:
```
{
    "data": [
        {
            "userId": "USER00001",
            "userName": "sudarshan",
            "email": "sudarshan@gmail.com",
            "firstName": "Sudarshan",
            "lastName": "NP",
            "dob": "1990-01-01",
            "addressDetails": [
                {
                    "addressId": 1,
                    "addressType": "HOME",
                    "addressLine01": "01, Park square",
                    "addressLine02": "Whitefield",
                    "addressLine03": "Bangalore",
                    "pinCode": "560066",
                    "landMark": "Next to Govn School",
                    "primaryContactNumber": "10000000000",
                    "secondaryContactNumber": "20000000000",
                    "createdBy": "ADMIN",
                    "createdDate": "2022-12-12T10:26:01.000+00:00",
                    "updatedDate": "2022-12-12T10:26:01.000+00:00"
                }
            ],
            "roles": [
                {
                    "roleId": 1,
                    "roleName": "ADMIN"
                }
            ]
        }
    ]
}
```

#### User Record By Id: [`http://localhost:8080/mapping/users/USER00001`]
##### Response:
```
{
    "data": {
        "userId": "USER00001",
        "userName": "sudarshan",
        "email": "sudarshan@gmail.com",
        "firstName": "Sudarshan",
        "lastName": "NP",
        "dob": "1990-01-01",
        "addressDetails": [
            {
                "addressId": 1,
                "addressType": "HOME",
                "addressLine01": "01, Park square",
                "addressLine02": "Whitefield",
                "addressLine03": "Bangalore",
                "pinCode": "560066",
                "landMark": "Next to Govn School",
                "primaryContactNumber": "10000000000",
                "secondaryContactNumber": "20000000000",
                "createdBy": "ADMIN",
                "createdDate": "2022-12-12T10:26:01.000+00:00",
                "updatedDate": "2022-12-12T10:26:01.000+00:00"
            }
        ],
        "roles": [
            {
                "roleId": 1,
                "roleName": "ADMIN"
            }
        ]
    }
}
```

#### Update User Record: [`http://localhost:8080/mapping/users/USER00001`]
##### Request:
```
{
    "firstName": "Sudarshan",
    "lastName": "NPS",
    "gender": "MALE",
    "dob": "1990-01-01",
    "roles": [
        {
            "roleId": 2
        }
    ],
    "addressDetails": [
        {
            "addressType": "OFFICE",
            "addressLine01": "01, Park square",
            "addressLine02": "Whitefield",
            "addressLine03": "Bangalore",
            "pinCode": "560066",
            "state": "KA",
            "city": "IND",
            "landMark": "Next to Govn School",
            "primaryContactNumber": "10000000000",
            "secondaryContactNumber": "20000000000"
        }
    ]
}
```

##### Response:
```
{
    "data": {
        "userId": "USER00001",
        "userName": "sudarshan",
        "email": "sudarshan@gmail.com",
        "firstName": "Sudarshan",
        "lastName": "NPS",
        "dob": "1990-01-01",
        "addressDetails": [
            {
                "addressId": 2,
                "addressType": "OFFICE",
                "addressLine01": "01, Park square",
                "addressLine02": "Whitefield",
                "addressLine03": "Bangalore",
                "pinCode": "560066",
                "landMark": "Next to Govn School",
                "primaryContactNumber": "10000000000",
                "secondaryContactNumber": "20000000000",
                "createdBy": "ADMIN",
                "createdDate": "2022-12-12T10:28:18.625+00:00",
                "updatedDate": "2022-12-12T10:28:18.625+00:00"
            }
        ],
        "roles": [
            {
                "roleId": 2,
                "roleName": "USER"
            }
        ]
    }
}
```

#### Delete User Record: [`http://localhost:8080/mapping/users/USER00001`]
##### Response:
```
{
    "data": "Successfully Deleted"
}
```




