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