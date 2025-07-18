package com.user.model;

//JPA annotation that marks this class as a database entity
import jakarta.persistence.*;

//Lombok annotations to generate boilerplate code
import lombok.*;

//Marks this class as a persistent JPA entity mapped to a database table
@Entity
//Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@Data
//Lombok annotation to generate a no-arguments constructor
@NoArgsConstructor
//Lombok annotation to generate a constructor with all fields
@AllArgsConstructor
//Lombok annotation to provide a builder pattern for creating instances
@Builder
//Specifies the database table name for this entity (defaults to class name if not specified)
@Table(name = "users")
public class User {

 // Marks this field as the primary key
 @Id
 // Specifies that the ID should be auto-generated by the database using the identity strategy
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;

 // Maps this field to a column that must be unique and not null
 @Column(nullable = false, unique = true)
 private String username;

 // Maps this field to a column that must not be null
 @Column(nullable = false)
 private String password;

 // Maps this field to a column that must be unique and not null
 @Column(nullable = false, unique = true)
 private String email;
}
