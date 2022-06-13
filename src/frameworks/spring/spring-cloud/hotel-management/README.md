



<br>

## Table of Contents
- [Tech stack of a project](#tech-stack-of-a-project)
- [How to connect H2 datbase](#how-to-connect-h2-datbase)
- [How to access guest-service](#how-to-access-guest-service)
- [Some problems when using Spring Cloud Config](#some-problems-when-using-spring-cloud-config)
- [Wrapping up](#wrapping-up)


<br>

## Tech stack of a project

1. Spring Cloud Config Server.

2. Spring Cloud Eureka Server.


<br>

## How to connect H2 datbase

To connection H2 database, follow the below link:
- https://www.javatpoint.com/spring-boot-h2-database

To reset the H2 configuration, follows some steps:
- Go to the user directory.
- Search the file: `.h2.server.properties`.
- Remove this file.

<br>

## How to access guest-service

1. Get all data from guest-service.

    - Go to `localhost:8800/guests`.


<br>

## Some problems when using Spring Cloud Config

1. No `spring.config.import` has been defined.

    - Currently, we're using the latest version of Spring cloud config server. So the properties will not be defined in `bootstrap.properties` file.
    
    - Then, we will config them in `application.properties` file.
 
    - Besides that, instead of using `spring-cloud-config-client` in pom.xml file of guest-service, we can use `spring-cloud-starter-config` dependency in pom.xml.
    
      ```xml
      <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-config</artifactId>
      </dependency>
      ```
 
    Refer to the [link](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/#_spring_cloud_config_client).


<br>

## Wrapping up



<br>

Reference:

[]()