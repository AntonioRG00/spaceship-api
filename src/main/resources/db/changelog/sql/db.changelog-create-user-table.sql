-- db/changelog/sql/db.changelog-create-spaceship-table.sql

CREATE TABLE MYUSER (
    ID NUMBER(32,0) AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(50) NOT NULL UNIQUE,
    PWD VARCHAR(500) NOT NULL
);