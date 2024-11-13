package com.pover.Library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDto {
    private Long book_id;
    private String title;
    private int publication_year;
    private boolean available;

    public BookResponseDto(Long book_id, String title, int publication_year, boolean available) {
        this.book_id = book_id;
        this.title = title;
        this.publication_year = publication_year;
        this.available = available;
    }
}
