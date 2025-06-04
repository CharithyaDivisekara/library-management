package com.example.library.entity;

import jakarta.persistence.Entity;// Brings in tools to connect this class to a database table
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;// Adds checks to make sure some fields are not empty
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Entity // Says this class will be saved in the database as a table.

@Data // Automatically creates getters, setters, and other common methods.
@NoArgsConstructor // Creates a constructor that needs no inputs.
@AllArgsConstructor // Creates a constructor that uses all fields as inputs.
@Builder // Lets you build objects in a flexible way.
public class Book {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //PK

    @NotBlank(message = "Title is required") // Makes sure the title is not empty.
    private String title;

    @NotBlank(message = "Author is required")// Makes sure the author name is not empty.
    private String author;

    @NotBlank(message = "ISBN is required") // Makes sure the ISBN is not empty
    @Column(unique = true)
    private String isbn;
    @Builder.Default
    private boolean available = true; // Shows if the book is available to borrow, true by default.
}