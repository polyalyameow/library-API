package com.pover.Library.repository;

import com.pover.Library.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUserIdAndReturnedDateIsNull(Long userId);
}
