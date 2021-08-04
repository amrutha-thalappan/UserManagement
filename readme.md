# User Management

This is a webservice platform provides a bare minimum functionality to store and view user details. It is developed as
JAVA spring boot application with rest API capabilities. These APIs handle user registration process and retrieval of a
particular user. More detailed technical specification about application interfaces, classes and methods are available in the javadoc documentation of project and you can find [Access Javadoc](#Access_Javadoc) section for more details.

## Features

#### User Registration

Provides POST api to save new user details the system. A user object has three mandatory attributes such as:

* username
* birthdate
* country

two optional attributes such as:

* Phone number
* Gender

This API restricts registration of a user with an existing username. Only adult French residents are allowed to register
to the system.

#### Show details of a registered user

Provides Get Api to view the details of registered user by giving username as input. If user does not exist with the
given username then corresponding error response will be shown.

## Quickstart

User Management service is available a buildable source code, but easiest way to install and run application is to use
pre-built application jar file available in
the [release](https://github.com/amrutha-thalappan/UserManagement/releases/tag/v1.0)

### Prerequisite

* Minimum Java 8
* Maven 3

### Install

#### Run the application from source

Ensure that maven installed in your environment before proceeding the installation steps. if no maven install please
follow the [maven installation and setup guide](#Maven_Install)

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

#### Run the application from pre-built package

* Download pre-built application jar "usermanagement-1.0.0.jar" from
  the [release](https://github.com/amrutha-thalappan/UserManagement/releases/tag/v1.0)
* open command prompt and change directory where usermanagement-1.0.0.jar is downloaded
* run the command  
  java -jar usermanagement-1.0.0.jar

### UserManual

Once the application has been started, the Swagger specification, User management APIs and Database can be accessed as
described below:

#### Access API Specification

* open http://localhost:8080 in browser in order to view the swagger specification about user management API

#### Access API

* Postman collection 'User_APIs.postman_collection.json' has been created and stored
  inside [postman_collection](https://github.com/amrutha-thalappan/UserManagement/tree/master/src/test/resources) folder
  inside project.
* Import and open the collection in Postman
* The successful registration API is stored inside 'User_Registration' folder (/User_APIs/User_Regstration)  
   * All the validation APIs are stored in a folder 'Error cases' inside 'User_Registration' folder (/User_APIs/User_Regstration/Error_Cases) 
* The successful user retrieval API is stored inside 'Retrieve_User' folder (/User_APIs/Retrieve_User)  
   * All the validation APIs are stored in a folder 'Error cases' inside 'Retrieve_User' folder (/User_APIs/Retrieve_User/Error_Cases)

#### Access Database

A folder called 'data' having the h2 database files will be created in the first run of the application. Data will be
restored to the database in each subsequent execution.  
To view the database:

* open http://localhost:8080/h2-console/ in browser  
  JDBC URL : jdbc:h2:file:./data/userdb  
  Username : admin  
  Password : admin
* Click the table 'USER' inside the database 'userdb'.
* Execute the query shown in the query box to view the table data.  

<div id="Access_Javadoc"></div>  

### Access Javadoc  

* After cloning the project, a [javadoc](https://github.com/amrutha-thalappan/UserManagement/tree/master/javadoc) folder can be seen inside UserMangement
* Open 'index.html' file inside the javadoc folder to see the javadoc documentation for classes, interfaces and methods  

<div id="Maven_Install"></div>  

### Install Maven

* Download [maven](https://maven.apache.org/download.cgi#)
* Follow following [installation steps](https://maven.apache.org/install.html)
* set M2_HOME, MAVEN_HOME to environment variable  
  example :  
  M2_HOME = C:\Program Files\Apache\apache-maven-3.8.1  
  M2_HOME = C:\Program Files\Apache\apache-maven-3.8.1
* Add the path of bin directory of the installed apache maven to the PATH environment variable
* Confirm with mvn -v in a new shell or command prompt
