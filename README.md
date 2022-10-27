# Spring Boot Modules
This repo includes the collection of spring boot modules

## Modules list:

`rest-api-mysql-junit-sonar` - this repo includes rest api's using spring framework, unit testing with junit & mockito and integration with sonarqube.

### Prerequisites
The following items should be installed in your system:
* Spring Tool Suite or any of your favourite tool
* MySQL server & MySql workbench
* Git
* Maven


### Technology Used:     
  * Java 8                                                                                                                                
  * Spring Boot & Spring Framework                                                                                                                    
  * JPA & Hibernate
  * Junit and Mockito
  * Sonarqube                                                                                                                  
                                                                                                                           
 
### Steps to clone the repository:

1) Download this Project from Git.
```
git clone https://github.com/Sudarshan-Gowda/spring-boot-modules.git
```
2) To Import the Project in your development tool.
```
File -> Import -> Maven -> Existing Maven project -> spring-boot-modules
```

### Build the project:

To build the whole project or all the module, you can execute the command - `mvn clean install`
To build specific repo, navigate to the module and execute the above mvn command.


### Steps to setup and run the sonaqube reports:
There are multiple sites available in the Internet to download and setup the sonarqube. 

If you are using mac-os you can make use of this site:
https://techblost.com/how-to-setup-sonarqube-locally-on-mac/

Execute the bellow command from your terminal to generate and view the sonarqube report.

```
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=spring-boot-modules \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=sqp_94052f99d01f6b7ff7a814b7832f74896d93e33a
```

replace the projectKey & login id in the above with your details.

