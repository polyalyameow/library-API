package com.pover.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pover.Library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
