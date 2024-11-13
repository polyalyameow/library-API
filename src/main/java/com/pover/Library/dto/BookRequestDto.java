package com.pover.Library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDto {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Publication year cannot be blank")
    @Pattern(
            regexp = "^(19|20)\\d{2}$",
            message = "Publication year must be a valid year in the format YYYY")
    private int publication_year;

    /*@NotBlank(message = "Author cannot be blank")
    private String author;*/

    private boolean available;

}
