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


