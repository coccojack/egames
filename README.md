# EGames
Ecommerce of videoGames

# Technologies
    - Database: MySql
    - Backend: SpringBoot

# Tips:
## Docker setup for MySql DB
```
docker run --name=application_db -d mysql -p 3306:3306
```
By default credential to access will be root | root @ localhost:3306

## Creating DB
Create a new db named as you like at mysql server address.

The name has to be set in application.properties file (exampled in example.properties as application_db).
