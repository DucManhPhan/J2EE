CREATE DATABASE common;

USE common;

CREATE TABLE `EMPLOYEE` (
    ID INT NOT NULL AUTO_INCREMENT,
    FULL_NAME VARCHAR(20) DEFAULT NULL,
    AGE INT DEFAULT NULL,

    PRIMARY KEY (ID)
);

INSERT INTO `employee` (ID, FULL_NAME, AGE) 
VALUES 
    (1, "John", 56), 
    (2, "Bill Adam", 45), 
    (3, "Mary Smith", 78);

SELECT * FROM `employee` order by id desc;