package com.pover.Library.controller;

import com.pover.Library.dto.BookResponseDto;
import com.pover.Library.dto.LoanRequestDto;
import com.pover.Library.dto.LoanResponseDto;
import com.pover.Library.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/loans")
@Validated
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<LoanResponseDto>> getAllLoans() {
        List<LoanResponseDto> allLoans = loanService.getAllLoans();
        return ResponseEntity.ok(allLoans);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanResponseDto> getLoanById(@PathVariable Long loanId) {
        LoanResponseDto loanResponseDto = loanService.getLoanById(loanId);
        return ResponseEntity.ok(loanResponseDto);
    }

    @PostMapping
    public ResponseEntity<LoanResponseDto> createLoan(@Valid @RequestBody LoanRequestDto loanRequestDto) {
        LoanResponseDto loanResponseDto = loanService.createLoan(loanRequestDto);
        return ResponseEntity.status(200).body(loanResponseDto);
    }

    @PutMapping("/return/{loanId}")
    @ResponseStatus(HttpStatus.OK)
    public LoanResponseDto returnBook(@PathVariable Long loanId) {
        return loanService.returnBook(loanId);
    }


    @GetMapping("/{user_id}/active")
    public ResponseEntity<List<LoanResponseDto>> getUserActiveLoans(@PathVariable Long user_id) {
        List<LoanResponseDto> activeLoans = loanService.getUserActiveLoans(user_id);
        return ResponseEntity.ok(activeLoans);
    }
}
