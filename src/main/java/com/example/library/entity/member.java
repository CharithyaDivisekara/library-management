package com.example.library.entity;

import jakarta.persistence.*;// Brings in tools to connect this class to a DB table
import jakarta.validation.constraints.Email; // Checks that the email in the right format
import jakarta.validation.constraints.NotBlank; // Makes sure some fields are not empty.
import lombok.*; //  Adds tools to automatically create common code like getters and setters

import java.time.LocalDate; // Java class to handle dates without time.

@Entity //marks this clz to be saved as a table in DB
@Data // Automatically creates getters, setters, and other useful methods
@NoArgsConstructor // Creates a constructor that needs no inputs.
@AllArgsConstructor // Creates a constructor that uses all fields as inputs.
@Builder //build obj. step by steps
public class  member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // The DB will give an ID no. automatically when a member is added.
    private Long id; //pk

    @NotBlank(message = "Name is required") // not empty 
    private String name;

    @Email(message = "Email should be valid") //valid format
    @NotBlank(message = "Email is required") // not empty
    @Column(unique = true) // Ensures every email is unique in the DB
    private String email; // member's email

    @Builder.Default
    private LocalDate membershipDate = LocalDate.now(); // The date the member joined; defaults - today’s date.
}