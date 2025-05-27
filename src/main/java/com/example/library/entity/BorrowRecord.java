package com.example.library.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private member member;

    @ManyToOne(optional = false)
    private Book book;

    //private final LocalDate borrowDate = LocalDate.now();
    private LocalDate borrowDate;
    private LocalDate returnDate; // nullable
}