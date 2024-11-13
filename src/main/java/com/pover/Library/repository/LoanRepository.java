package com.pover.Library.repository;

import com.pover.Library.model.Loan;
import com.pover.Library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    default List<Loan> findByUser_UserIdAndReturnedDateIsNull(Long user_id) {
        return null;
    }
}
