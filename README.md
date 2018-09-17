# Assumptions
This is a simple demo application for number block management, so a lot of complex features are not included, for example:
1. number recycle
2. event parameter only store simple string
3. unit test cases

# Environment
* IDE: Spring Tool Suite 3.9.2 RELEASE
* OS: Windows 8
* DATABASE: PostgreSQL 10.5
* JDK: Java 8
* Build: Gradle 4.10.1

# Database
* Download version 10.5 from https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
* Install and set password to "postgres" for root user "postgres" during installation
* Launch pgAdmin 4 and create a database "mr"

# Backend
* Go to [project root]/resources
* Execute: java -jar mr-code-challenge-1.0.0.jar
* Database tables will be created automatically
* The following numbers have been pre-populated: 90000001 - 90000005
* The following addons have been pre-populated: Call Forwarding, Voice Mail, Caller ID, Roaming
* The backend can be compiled to a Docker image with Gradle docker task but database is not set up for it due to time constraint

# Testing
## Using Swagger
* Start backend and access http://localhost:8080/swagger-ui.html  

## Using Postman  
* Download Postman from https://dl.pstmn.io/download/version/5.5.3/windows64
* Install
* Import [project root]/resources/postman.v2.json and start testing from there

