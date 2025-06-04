package com.example.library.entity;

import jakarta.persistence.*;// Brings in tools to connect this class to a DB
import lombok.*; // Adds tools to automatically create common code like getters and setters

import java.time.LocalDate; //// Used to handle dates without time.

@Entity //marks this clz to be saved as a table in DB
@Data // Automatically creates getters, setters, and other useful methods
@NoArgsConstructor // Creates a constructor with no input
@AllArgsConstructor// Creates a constructor with all fields inputs.
@Builder //build obj. step by steps

public class BorrowRecord {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk

    @ManyToOne(optional = false) // Many borrow records can be linked to one member; member is required.
    private member member; // member who borrowed book

    @ManyToOne(optional = false) // Many borrow records can be linked to one book; book is required.
    private Book book; // book that was borrowed.

    //private final LocalDate borrowDate = LocalDate.now();
    private LocalDate borrowDate; // date the book was borrowed; defaults : today.
    private LocalDate returnDate; //date the book was returned; can be empty if not returned yet 
}