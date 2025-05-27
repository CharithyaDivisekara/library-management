package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BorrowRecordRepository borrowRecordRepository;

    public Book addBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException("Book with this ISBN already exists");
        }
        book.setAvailable(true);
        return bookRepository.save(book);
    }
    //Specify List<Book> instead of raw List
    public List <Book> getAllBooks() {
        return bookRepository.findAll();
    }


    //Specify List<Book> instead of raw List
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword);
    }
    public Book updateBook(Long id, Book updatedBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setIsbn(updatedBook.getIsbn());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (borrowRecordRepository.existsByBookIdAndReturnDateIsNull(id)) {
            throw new IllegalStateException("Book is currently borrowed and cannot be deleted");
        }
        bookRepository.deleteById(id);
    }
}