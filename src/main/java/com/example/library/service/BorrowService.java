package com.example.library.service;

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

@Service
@RequiredArgsConstructor
public class BorrowService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public BorrowRecord borrowBook(Long memberId, Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available for borrowing");
        }

        member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        book.setAvailable(false);
        bookRepository.save(book);

        BorrowRecord record = BorrowRecord.builder()
                .book(book)
                .member(member)
                .borrowDate(LocalDate.now())
                .build();

        return borrowRecordRepository.save(record);
    }

    public BorrowRecord returnBook(Long borrowRecordId) {
        BorrowRecord record = borrowRecordRepository.findById(borrowRecordId)
                .orElseThrow(() -> new IllegalArgumentException("Borrow record not found"));

        if (record.getReturnDate() != null) {
            throw new IllegalStateException("Book already returned");
        }

        record.setReturnDate(LocalDate.now());
        Book book = record.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return borrowRecordRepository.save(record);
    }

    public List<BorrowRecord> getBorrowHistory(Long memberId) {
        return borrowRecordRepository.findByMemberId(memberId);
    }
}