# Spring Core – IoC and Bean Management Lab

## Overview
This project demonstrates the basic concepts of Spring Core, focusing on Inversion of Control (IoC), Dependency Injection (DI), and Bean Management using XML-based configuration.

## Concepts Covered
- **Inversion of Control (IoC):** Using the Spring IoC container to manage object creation.
- **Setter Injection:** Injecting values and dependencies through setter methods.
- **Constructor Injection:** Injecting values and objects through constructors.
- **Literal Dependency Injection:** Injecting simple values such as strings and numbers.
- **Object Dependency Injection:** Injecting an Address object into a Student bean.
- **Collection Injection:** Injecting a collection of Subject objects using XML configuration.
- **Bean Scopes:**
  - Singleton: Returns the same bean instance for every request.
  - Prototype: Creates a new bean instance for each request.

## Technologies Used
- Java
- Spring Framework (Spring Context)
- Maven
- XML Configuration

## Project Structure
```text
my_springcore/
├── pom.xml
└── src/
    └── main/
        ├── java/com/craft/
        │   ├── App.java
        │   ├── Student.java
        │   ├── Address.java
        │   └── Subject.java
        └── resources/
            └── beansConfig.xml
```

## How to Run
1. Open the project in IntelliJ IDEA.
2. Make sure Maven dependencies are loaded.
3. Open `App.java`.
4. Run the `main()` method.

Alternatively, run the following command in the project terminal:

```bash
mvn clean install
```

## Expected Result
The application displays student information and demonstrates setter injection, constructor injection, object and collection dependency injection, and the difference between Singleton and Prototype bean scopes.
