# EGames
Ecommerce of videoGames

# Technologies
    - Database: MySql
    - Backend: SpringBoot
    - Frontend: React

# Tips:
## Docker container setup for MySql DB - using cmd in windows (change USERPROFILE variable to use powershell or bash)
```
docker run --name=egames_db -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -v %USERPROFILE%\.docker\volumes\egames:/var/lib/mysql mysql
```
By default credential to access will be root | root @ localhost:3306

## Creating DB
Create a new db named as you like at mysql server address.

The name has to be set in application.properties file (exampled in example.properties as application_db).

# Front-end Stack
- React
- Redux
- Bootstrap

## Install dependencies

  npm install

## Start the server

  npm start
