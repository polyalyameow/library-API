package com.pover.Library.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BookResponseDto {
    private Long book_id;
    private String title;
    private int publication_year;
    private boolean available;
    private String genreName;
    private String authorFirstName;
    private String authorLastName;

    public BookResponseDto(Long book_id, String title, int publication_year, boolean available, String genreName, String authorFirstName, String authorLastName) {
        this.book_id = book_id;
        this.title = title;
        this.publication_year = publication_year;
        this.available = available;
        this.genreName = genreName;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }
}
