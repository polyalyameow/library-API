package com.pover.Library.dto;

import com.pover.Library.model.Book;
import com.pover.Library.model.Loan;
import com.pover.Library.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LoanResponseDto {

    private Long loan_id;
    private Book book;
    //private UserResponseDto user;
    private LocalDate loan_date;
    private LocalDate due_date;
    // private LocalDate returned_date;

    public LoanResponseDto(Loan loan) {
        this.loan_id = loan.getLoan_id();
        this.book = loan.getBook();
        //this.user = new UserResponseDto(loan.getUser());
        this.loan_date = loan.getLoan_date();
        this.due_date = loan.getDue_date();
       // this.returned_date = loan.getReturned_date();
    }

}
