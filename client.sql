CREATE DATABASE IF NOT 
EXISTS client;
USE client;

DROP TABLE IF EXISTS message;
DROP TABLE IF EXISTS client;


CREATE TABLE client (
c_id int(11) NOT NULL AUTO_INCREMENT,
login varchar(32) NOT NULL,
password varchar(32),
dateConnection datetime,
salt varchar(1000),
PRIMARY KEY (c_id),
 UNIQUE KEY idx_cname (login)

);
INSERT INTO client(login, dateConnection) VALUES ('oleg', CURRENT_TIME()), ('ma', CURRENT_TIME());
SELECT * FROM client;


CREATE TABLE message (
m_id int(11) NOT NULL AUTO_INCREMENT,
m_message VARCHAR(1000) NOT NULL,
login_from VARCHAR(32),
login_to VARCHAR(32),
dateCreate datetime,
PRIMARY KEY (m_id)

);
INSERT INTO message(m_message, login_from,dateCreate) VALUES ('hello',1,CURRENT_TIME()), ('hi',2,CURRENT_TIME());
SELECT * FROM message;
