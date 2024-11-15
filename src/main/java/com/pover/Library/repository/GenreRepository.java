package com.pover.Library.repository;

import com.pover.Library.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
