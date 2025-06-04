package com.example.library.service;
// Imports needed clz like entities, repositories, and Lombok tools
import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;
import java.util.Optional;

@Service // Marks this clz as a service in the Spring application.
@RequiredArgsConstructor // Automatically creates a constructor for all final fields (repo.)
public class BookService {
    private final BookRepository bookRepository; 
    private final BorrowRecordRepository borrowRecordRepository;// Used to check borrowing records in the DB


    public Book addBook(Book book) {
        /*
        - Adds a new book to the system.
        - Checks if the book's ISBN is already in use. If yes, stops and shows an error.
        - Sets the book as available by default.
        -  Saves the book and returns it
        */
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException("Book with this ISBN already exists");
        }
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    //Specify List<Book> instead of raw List
    public List <Book> getAllBooks() {
        /*
         - Gets a list of all books in the database.
         - Returns the list directly
         */
        return bookRepository.findAll();
    }


    //Specify List<Book> instead of raw List
    public List<Book> searchBooks(String title,String author) {
        /*
        - Finds books where the title or author matches the search word (ignores case).
        - Uses the database query to find matching books.
         */
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(title, author);
    }


    public Book updateBook(Long id, Book updatedBook) {
        /*
        - Updates details of an existing book.
        - Looks for the book by its ID, throws error if not found.
        - Changes the title, author, and ISBN to new values.
        - Saves and returns the updated book.
        */

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id) {
         return bookRepository.findById(id);
}


    public void deleteBook(Long id) {
        /*
        - Deletes a book by its ID.
        - Checks if the book is currently borrowed and not returned. If yes, throws error.
        - If the book is safe to delete, removes it from the DB.
         */

        if (borrowRecordRepository.existsByBookIdAndReturnDateIsNull(id)) {
            throw new IllegalStateException("Book is currently borrowed and cannot be deleted");
        }
        bookRepository.deleteById(id);
    }
}