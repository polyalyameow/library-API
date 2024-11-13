package com.pover.Library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loan_id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

   // @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private LocalDate loan_date;

    @NotNull
    private LocalDate due_date;

    @Column(name = "returned_date")
    private LocalDate returned_date;

}
