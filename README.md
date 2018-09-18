# Assumptions
This is a simple demo application for number block management, so a lot of complex features are not included, for example:
1. number recycle
1. event parameter only store simple string
1. API authorization (e.g. for customer and administrator)
1. unit test cases

# Solution
## Architecture
![Architecture diagram](./resources/architecture.png)

There is one micro-service in the system which has three layers of objects:
1. Controller: end point for REST API
1. Service: contains business logic
1. Repository: for data access

There are three controllers:
1. Number Controller: provides APIs for number provision, quarantine, release, subscribe and terminate.
1. Addon Controller: provides APIs for addons (pre-populated) retrieval
1. Event Controller: provides APIs for events retrieval

Details can be find this diagram.
![APIs](./resources/API.png)

You can also see APIs after application started and access the Swagger UI. (see the Testing section)

## Database
![ERD](./resources/ERD.png)

## Scalability
1. Horizontal scalability: the micro-service can scale horizontally, probably based on number range. UUID is used as primary keys in the database, so there is conflict among micro-services.
1. Vertical scalability: number management, service management and event management may be split into separate micro-services if traffic volume or data storage increases.   
In this case, reference keys and parent-child associations should be removed.

## Performance
1. Scalability ensures manageable traffic volume and data storage on each server
1. Pagination is used for number/event retrieval
1. Indices are used for speeding up some quires (e.g. get events order by number id)

## Race condition handling
1. Unique constraint is added to ensure there is no multiple provisioning of the same number
1. Version is added for detecting conflict updates (quarantine, release, subscribe and terminate)

# Environment
* IDE: Spring Tool Suite 3.9.2 RELEASE
* OS: Windows 8
* DATABASE: H2 (In-memory)
* Language and Framework: Java 8 and Spring Boot 2
* Build: Gradle

# Provisioned Data
* Database tables will be created automatically
* The following numbers have been pre-populated: 90000001 - 90000005
* The following addons have been pre-populated: Call Forwarding, Voice Mail, Caller ID, Roaming

# Execution
There are two ways to run the application.

## As Docker image
* Install Docker environment
* Execute: docker pull ivxivx/mr-code-challenge:1.0.0
* Execute: docker run -p 8080:8080 ivxivx/mr-code-challenge:1.0.0

## As Spring Boot Application
* Go to [project root]/resources
* Execute: java -jar mr-code-challenge-1.0.0.jar

# Testing
## Using Swagger
* Start backend and access http://localhost:8080/swagger-ui.html  

## Using Postman  
* Download Postman from https://dl.pstmn.io/download/version/5.5.3/windows64
* Install
* Import [project root]/resources/postman.v2.json and start testing from there
