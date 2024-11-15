package com.pover.Library.repository;

import com.pover.Library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findById(long id);

}
