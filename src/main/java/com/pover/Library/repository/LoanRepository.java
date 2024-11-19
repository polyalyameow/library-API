package com.pover.Library.repository;

import com.pover.Library.model.Book;
import com.pover.Library.model.Loan;
import com.pover.Library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUserAndReturnedDateIsNull(User user);
    boolean existsByBook_BookId(Long bookId);

    // Custom query to check if a book is already loaned out
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END FROM Loan l WHERE l.book.id = :bookId AND l.returnedDate IS NULL")
    boolean existsByBookIdAndReturnedDateIsNull(@Param("bookId") Long bookId);

    // Optionally, find a loan by book ID and ensure it’s not returned
    Loan findByBookAndReturnedDateIsNull(Book book);
    List<Loan> findByUser(User user);
}
