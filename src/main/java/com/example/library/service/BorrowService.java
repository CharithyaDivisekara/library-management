package com.example.library.service;
// Imports classes for Book, BorrowRecord, Member entities and their repositories
import com.example.library.entity.Book;
import com.example.library.entity.BorrowRecord;
import com.example.library.entity.member;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRecordRepository;
import com.example.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service // Marks this clz as a service in the Spring application.
@RequiredArgsConstructor // Marks this clz as a service in the Spring application.(repo.)
public class BorrowService {
    private final BorrowRecordRepository borrowRecordRepository; // Repo. to access borrow records in the DB
    private final BookRepository bookRepository; //Repo. to access books in the DB
    private final MemberRepository memberRepository; // Repo. to access members in the DB

    public BorrowRecord borrowBook(Long memberId, Long bookId) { 
        // Method to let a member borrow a book by their IDs

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
                 // Look for the book by its ID, if not found, error
        
                 if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available for borrowing");
        }  // If the book is not available, throw error

        member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
                 // If the book is not available, error
        
                 
                 book.setAvailable(false); // If the book is not available,error

        bookRepository.save(book);  // Save the updated book availability to the DB

        BorrowRecord record = BorrowRecord.builder()
                .book(book)
                .member(member)
                .borrowDate(LocalDate.now())
                .build();

        return borrowRecordRepository.save(record);  // Save the borrow record and return it
    }

    public BorrowRecord returnBook(Long borrowRecordId) {    // Method to return a borrowed book using the borrow record ID
        BorrowRecord record = borrowRecordRepository.findById(borrowRecordId)
                .orElseThrow(() -> new IllegalArgumentException("Borrow record not found"));
           // Method to return a borrowed book using the borrow record ID
        
           if (record.getReturnDate() != null) {
            throw new IllegalStateException("Book already returned");
        }
        // If the book has already been returned (return date is set), error
        record.setReturnDate(LocalDate.now());  // Set the return date to today’s date
        Book book = record.getBook(); 
        book.setAvailable(true);  // Mark the book as available again
        bookRepository.save(book);// Save the updated book status to the DB

        return borrowRecordRepository.save(record);// save update borrow recode and return it
    }

    public List<BorrowRecord> getBorrowHistory(Long memberId) {
         // Method to get all borrow records for a specific member
        return borrowRecordRepository.findByMemberId(memberId);
          // Return the list of borrow records for the given member ID
    }


    
}