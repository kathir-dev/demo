# demo project
This project is to create REST API's which handles Person details as a web resource.
It provides options like ADD, LIST, GETBYID, DELETE, UPDATE the resource "People" 
which is plural for "Person"

These REST API's are secured with Spring Security to authenticate the users who 
access these API's using the JWT token. The security layer provides Authorization
based on the ROLES like "USER" & "ADMIN".

Technology stack:
Spring Boot, Spring Security, JWT, JPA, REST, H2 - In Memory Database.

How to Run this Application:
This is Maven Project where all the Spring Boot & Spring Security related dependencies
are configured in the pom.xml

1. Clone this repository, https://github.com/kathir-dev/demo.git
2. From the Maven installed system, execute # mvn clean install.
3. After successful execution, a JAR file (named "demo-0.0.1-SNAPSHOT") will be generated 
inside the target folder.
4. Use the command to start the server, # java -jar demo-0.0.1-SNAPSHOT.jar \ --server.port=9090

How to Test this Application:
There are two parts in this application where 1.REST Controller to handle the CRUD operations 
for the Person details, 2. Security layer which provides the Authentication through token.

1. Use the REST API "/auth/authenticate" to get the token by passing in the credentials 
as a JSON object. After the successful authentication, a token will be provided.
{ "username": "user", "password":"password"}, {"username":"admin","password":"passwor"}
2. Use the token provided in the further requests. 
3. Any POST, PUT, DELETE method execution on the REST resource would need admin privileges. 
4. GET method to list the people would require USER privileges.

URL Mapping:
List the person - "/people"
Get the person - "/people/{id}"
Update the person - "/people/{id}" with complete Person object with modified values in JSON format.
Delete the Person - "/people/{id}" 

Note: I have used the "personId" to identify the "person" which was not there in the test requirement.
Also, I have chosen to add the security instead of the Unit test cases.
