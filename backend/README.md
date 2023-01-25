## Front-end Stack
- Springboot
- Java 17
- Mysql database


## Database Setup:
## Docker container setup for MySql DB:
- using cmd in windows (change USERPROFILE variable to use powershell or bash)
```
docker run --name=egames_db -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -v %USERPROFILE%\.docker\volumes\egames:/var/lib/mysql mysql
```
By default credential to access will be root | root @ localhost:3306

## Creating DB
- Create a new db named as you like at mysql server address. (The name has to be set in application.properties file (exampled in example.properties as application_db).

- Execute backend/sql/tables.sql to create tables an next execute backend/sql/initialize_tables.sql to initialize tables with example data.
