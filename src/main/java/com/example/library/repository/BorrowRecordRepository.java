package com.example.library.repository;

import com.example.library.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
     // Inherits basic DB operations for BorrowRecord entity (save, delete, findById)

    List<BorrowRecord> findByMemberId(Long memberId);
    // Finds all borrow records for a specific member using their ID

    boolean existsByBookIdAndReturnDateIsNull(Long bookId);
     // Checks if there is any borrow record for the book that has not been returned yet
}
