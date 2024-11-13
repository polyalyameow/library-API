package com.pover.Library.dto;

import com.pover.Library.model.Book;
import com.pover.Library.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class LoanRequestDto {

    @NotNull
    private Book book;

    @NotNull
    private User user;

    @NotNull
    private LocalDate due_date;

    @NotNull
    private LocalDate returned_date;

}
