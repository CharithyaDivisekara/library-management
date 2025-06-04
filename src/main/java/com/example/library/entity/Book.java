package com.example.library.entity;

import jakarta.persistence.Entity;// Brings in tools to connect this class to a DB table
import jakarta.persistence.Id; //Says which field is the PK of the table
import jakarta.persistence.GeneratedValue; //Lets the database automatically generate the ID value
import jakarta.persistence.GenerationType; //Allows choosing how the ID is generated (auto-increment)
import jakarta.persistence.Column;//Used to set extra details for a column (name or size)
import jakarta.validation.constraints.NotBlank;// Adds checks to make sure some fields are not empty
import lombok.Data; //Automatically adds common methods like getters, setters, toString
import lombok.NoArgsConstructor;//Creates a constructor with no input
import lombok.AllArgsConstructor;// Creates a constructor with all fields inputs.
import lombok.Builder;//build obj. step by steps
import com.example.library.entity.Book;

@Entity //marks this clz to be saved as a table in DB

@Data // Automatically creates getters, setters, and other common methods.
@NoArgsConstructor // Creates a constructor that needs no inputs.
@AllArgsConstructor // Creates a constructor that uses all fields as inputs.
@Builder //build obj. step by steps
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