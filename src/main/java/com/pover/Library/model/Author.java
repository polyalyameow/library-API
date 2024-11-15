package com.pover.Library.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long author_id;

    @NotNull
    private String first_name;
    @NotNull
    private String last_name;

    public String getName() {
        return first_name + " " + last_name;
    }

    private LocalDate birth_date;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Book> books;

}
