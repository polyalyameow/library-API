package com.pover.Library.service;

import com.pover.Library.dto.LoanRequestDto;
import com.pover.Library.dto.LoanResponseDto;
import com.pover.Library.model.Book;
import com.pover.Library.model.Loan;
import com.pover.Library.repository.LoanRepository;
import com.pover.Library.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
    }

    public LoanResponseDto getLoanById(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(()  -> new IllegalArgumentException("Loan not found"));
        return new LoanResponseDto(loan);
    }

    @Transactional
    public LoanResponseDto createLoan(LoanRequestDto loanRequestDto) {
        Book book = loanRequestDto.getBook();

        if (!book.isAvailable()) {
            throw new IllegalArgumentException("Book is not available for loan.");
        }

        book.setAvailable(false);
        bookRepository.save(book);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(loanRequestDto.getUser());
        loan.setLoan_date(LocalDate.now());
        loan.setDue_date(loanRequestDto.getDue_date());
        loanRepository.save(loan);

        return  new LoanResponseDto(loan);

    }

    @Transactional
    public LoanResponseDto returnBook(Long loanId) {

        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        loan.setReturned_date(LocalDate.now());

        Book book = loan.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        loanRepository.save(loan);
        return new LoanResponseDto(loan);
    }

    @Transactional
    public LoanResponseDto updateLoanDueDate(Long loanId, LocalDate newDueDate) {

        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new IllegalArgumentException("Loan not found"));

        if (!newDueDate.isAfter(loan.getDue_date())) {
            throw new IllegalArgumentException("Please make sure that your new due date is after current due date.");
        }

        loan.setDue_date(newDueDate);
        loanRepository.save(loan);

        return new LoanResponseDto(loan);
    }

    public List<LoanResponseDto> getUserActiveLoans(Long userId) {

        List<Loan> activeLoans = loanRepository.findByUserIdAndReturnedDateIsNull(userId);

        return activeLoans.stream()
                .map(LoanResponseDto::new)
                .collect(Collectors.toList());
    }

}
