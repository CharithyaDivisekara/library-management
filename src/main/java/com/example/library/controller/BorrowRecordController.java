package com.example.library.controller;

import com.example.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrow-records")
public class BorrowRecordController {

    @Autowired
    private BorrowService borrowRecordService;

    @PostMapping
    public String borrowBook(@RequestParam Long memberId, @RequestParam Long bookId) {
        borrowRecordService.borrowBook(memberId, bookId);
        return "Book borrowed successfully.";
    }

    @PutMapping("/{borrowRecordId}")
    public String returnBook(@PathVariable Long borrowRecordId) {
        borrowRecordService.returnBook(borrowRecordId);
        return "Book returned successfully.";
    }

    @GetMapping("/{memberId}")
    public Object getBorrowHistory(@PathVariable Long memberId) {
        return borrowRecordService.getBorrowHistory(memberId);
    }
}
