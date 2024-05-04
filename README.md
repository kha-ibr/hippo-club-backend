# Hippo Club 
Spring Boot with JPA Authentication, Spring Data JPA with MySQL, and Spring MVC are all used in the development of Hippo Club.
The frontend is made with React, and the source code for it is available here. https://github.com/hippoClub/hippo-club-frontend

##### Deployed app Link: https://hippo-club.herokuapp.com/login

### Application Preview

![Application preview](https://media.giphy.com/media/pHp2vxCKFSs4mLcCuZ/giphy.gif)

### Requirements
For building and running the application you need:
Java 11<br/>
Apache Maven

### Dependencies
* Spring Boot
* Spring Web
* Spring JPA
* Spring MySQL driver

### For cloners
To clone and start this project you will only need to have the following: 
Create a file called `application.properties` inside "src/main/resources/application.properties" and paste the following:
   ```
   spring.datasource.url=jdbc:mysql://{hostname}:3306/{schema name}
   spring.datasource.username={database username, e.g. root}
   spring.datasource.password={database password, e.g. password}
   spring.jpa.hibernate.ddl-auto=update
   spring.datasource.driver-class-name=com.mysql.jdbc.Driver
   spring.jpa.show-sql=true
   ```   
### Frontend code
The Frontend port was build using React and here is the code for it: https://github.com/hippoClub/hippo-club-frontend
