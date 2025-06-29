A console-based Student Management System built with Java 8, JDBC, and MySQL following clean architecture principles.


- Add, update, delete, and fetch students
- List all students in tabular format
- JDBC with prepared statements
- Java 8 features (Streams, Lambdas, Optionals)
- Layered architecture (Entity, DAO, Service, UI)
- Externalized DB configuration via `db.properties`
- Exception handling and file-based logging
- Easily testable with JUnit
- Simple file-based logging to `logs/student-management.log`


---

### 1. Create DB & Table

```sql
CREATE DATABASE IF NOT EXISTS studentdb;
USE studentdb;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    course VARCHAR(100) NOT NULL
);


##Configure db.properties
File: resources/db.properties

jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/studentdb
jdbc.username=root
jdbc.password=yourpassword

###How to Run

Open the project in Eclipse

Make sure resources/ is on the classpath

Add MySQL JDBC Driver (mysql-connector-java-8.x.x.jar) to your classpath

Run Main.java (Console UI)

Follow on-screen menu