package com.pover.Library.controller;

import com.pover.Library.dto.LoanRequestDto;
import com.pover.Library.dto.LoanResponseDto;
import com.pover.Library.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
@Validated
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
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
}
