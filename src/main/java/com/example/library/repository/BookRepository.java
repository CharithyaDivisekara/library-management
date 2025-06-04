package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Inherits basic DB operations for Book entity (save, delete, findById)

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
     // Find books where the title or author contains the given word 
    
    boolean existsByIsbn(String isbn);
    // Checks if a book with the given ISBN already exists in the DB
}