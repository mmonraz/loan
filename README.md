# loan

To be able to run the application we need to do the following steps

Access as root to your mysql database and perform the below DCL queries

Creating the database user
CREATE USER 'testUser'@'localhost' IDENTIFIED BY 'password';

As root still we create a database named podcast and we then execute the below query to grant the new user full access
GRANT ALL PRIVILEGES ON podcast . * TO 'testUser'@'localhost';

If the port on your MySQL database different from the default port 3306 then please go to the resources folder and update the properties file to have the correct port number spring.datasource.url = jdbc:mysql://localhost:3306/podcast

If all the previous step where done correctly you just need to run the application by PodcastApplication class main method

The application will create all the required database tables
