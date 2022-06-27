



<br>

## Table of Contents
- [Tech stack in this project](#tech-stack-in-this-project)
- [How to configure H2 database](#how-to-configure-h2-database)
- [Wrapping up](#wrapping-up)


<br>

## Tech stack in this project

1. Spring frameworks

    - Spring Data JPA
    - Spring Web
    
2. Testing frameworks

    - JUnit 5
    - Mockito
    - AssertJ


<br>

## How to configure H2 database

```properties
spring.jpa.hibernate.ddl-auto=none

spring.application.name=book-management-service

spring.datasource.url=jdbc:h2:mem:book-management-service
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.h2.console.enabled=true
```


<br>

## 





<br>

## Wrapping up




<br>
