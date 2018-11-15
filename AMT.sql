drop database if exists AMT;
create database AMT;

use AMT;

create table app_users(
	username varchar(20),
    PRIMARY KEY (username)
);

create table  dev_users(
	firstName varchar(20),
    lastName varchar (20),
    email varchar(50),
    passwd varchar(20), 
    isBeingReseted TINYINT(1) NOT NULL,
    isDisabled TINYINT(1) NOT NULL,
    isAdmin TINYINT(1) NOT NULL,
    PRIMARY KEY(email)
);

create table applications(
	appName varchar(20),
    appOwner varchar(20),
    keyAPI int,
	keySecret int,
    description text,  
    foreign key (appOwner) references dev_users(email),
    PRIMARY KEY(appName)
);


create table userScore(
	username varchar(20),
    appID varchar(20),
    nbPoints int,
	foreign key (appID) references applications(appName),
	foreign key (username) references app_users(username)
);

create table rules(
	appID varchar(20),
    rule varchar(50),
    nbPoints int,
    foreign key (appID) references applications(appName)
);

INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES ("a","a","admin@admin.com","a",0,0,1);
INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES ("a","a","admin1@admin.com","a",0,0,0);
INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES ("a","a","admin2@admin.com","a",0,0,0);
INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES ("a","a","admin3@admin.com","a",0,0,0);
INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES ("a","a","admin4@admin.com","a",0,0,0);
INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES ("a","a","admin5@admin.com","a",0,0,0);
INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES ("a","a","admin6@admin.com","a",0,0,0);
INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES ("a","a","admin7@admin.com","a",0,0,0);
INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES ("a","a","admin8@admin.com","a",0,0,0);
INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES ("a","a","admin9@admin.com","a",0,0,0);
INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES ("a","a","admin10@admin.com","a",0,0,0);
INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES ("a","a","admin11@admin.com","a",0,0,0);
INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES ("a","a","admin12@admin.com","a",0,0,0);
