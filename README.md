# AMT_PROJET

## How do deploy ?

### Wildfly

The first thing to do is to go to the managment side on Wildfly and to create a Mail resource and a DB resource in order to run the project.

### Add an admin to wildfly

Go to your wildfly server directory in the bin directory and do thefollowing command in your git console :
add-user.sh admin admin --silent

This will add an admin user with name admin and password admin.


### DB resource :



### Deploy the sql driver :

You have to download the last mysql connector 'https://dev.mysql.com/downloads/connector/j/' or use the one in the images/wildfly directory

Then you go in the Wildfly management:

Deployment -> + Upload a Deployment -> Select the mysql driver.

### Add a Datasource for sql

In the management :

Configuration -> Subsytems -> Datasource & Drivers -> Datasource -> click on + and annd a Datasource.  
For the Datasource do the following for each part :
```
1) Select mysql  
2) Name = AMT  
   JNDI name = java:/AMT  
3) JDB driver : select the on you deployed  
4) Connection URL : jdbc:mysql://localhost:3306/AMT  
   User Name  : your localhost mysql username
   Password : your localhost mysql password
5) Test the connction
```

### Email ressource

### Add a smtp socket :

In the managment: 

Go to Configuration -> socket Bindings -> standard socket -> view

The you do  :

```
go to Outbound remote
add : Host : smtp.gmail.com
      Port : 465
```

Then you go Configuration -> Subsytems -> Mail -> add

Then you do :

```
Name : Gmail  
JNDI name : java:jboss/mail/gmail
```

### Run it

Then you can deploy the war on the server and use it on localhost:8080.

The admin account is:  admin@admin.com and the password is :a 
