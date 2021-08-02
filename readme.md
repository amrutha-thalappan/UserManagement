# User Management
This is a webservice platform provides a bare minimum functionality to store and view user details. It is developed as JAVA spring boot application with rest API capabilities.
These APIs handle user registration process and retrieval of a particular user.
## Features
* User Registration  
xxxx
* List details of a registered user  
xxxxxx

## Quickstart
User Management service is available a buildable source code, but easiest way to install and run application is to use
pre-built application jar file available in the [release](https://github.com/amrutha-thalappan/UserManagement/releases/tag/v1.0) 
### Prerequisite
* Minimum Java 8
* Maven 3
### Install
#### Run the application from source 
Ensure that maven installed in your environment before proceeding the installation steps.
if no maven install please follow the [maven installation and setup guide](#Maven_Install)
* clone the project source code  
git clone https://github.com/amrutha-thalappan/UserManagement.git
* change the directory to source code folder  
cd UserManagement
* Build and install the application package  
mvn clean install
* if just want to run the unit test alone  
mvn test
* launch the application   
mvn spring-boot:run  
#### Run the application from pre built package
* Download pre-built application jar "usermanagement-1.0.0.jar" from the [release](https://github.com/amrutha-thalappan/UserManagement/releases/tag/v1.0)
* open command prompt and change directory where usermanagement-1.0.0.jar is downloaded  
* run the command  
  java -jar usermanagement-1.0.0.jar
### UserManual
#### Access API Specification
* open http://localhost:8080 in browser in order to view the swagger specification about user management API
#### Access API
about API tesing with postman
#### Access Database
* open http://localhost:8080/h2-console/ in browser  
username : admin  
password : admin  

<div id="Maven_Install"></div>  

### Install Maven
* Download [maven](https://maven.apache.org/download.cgi#) 
* Follow following [installation steps](https://maven.apache.org/install.html) 
* set M2_HOME, MAVEN_HOME to environment variable  
example :  
  M2_HOME = C:\Program Files\Apache\apache-maven-3.8.1  
  M2_HOME = C:\Program Files\Apache\apache-maven-3.8.1
* Add the bin directory of the installed apache maven to the PATH environment variable  
* Confirm with mvn -v in a new shell or command prompt
