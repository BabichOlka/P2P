CREATE DATABASE IF NOT EXISTS server;
USE server;
DROP TABLE IF EXISTS cookies;

CREATE TABLE cookies (
idcookies int NOT NULL AUTO_INCREMENT,
login varchar(45) DEFAULT NULL,
dateConection datetime DEFAULT NULL ,
keyu varchar(45) DEFAULT NULL,
PRIMARY KEY (idcookies)
); 
INSERT INTO cookies(login, dateConection ) VALUES ('o', CURRENT_TIME());
SELECT * FROM cookies;