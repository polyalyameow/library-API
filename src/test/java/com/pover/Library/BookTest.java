package com.pover.Library;

import com.pover.Library.model.Author;
import com.pover.Library.model.Book;
import com.pover.Library.model.Genre;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testIsBookWorking() {
        Author author = mock(Author.class);
        Genre genre = mock(Genre.class);

        System.out.println("Test isBookWorking started");

        when(author.getFirst_name()).thenReturn("Ivan");
        when(author.getLast_name()).thenReturn("Ivanov");

        when(genre.getName()).thenReturn("Fantasy");

        Set<Genre> genres = new HashSet<>();
        genres.add(genre);


        Book book = new Book();

        book.setTitle("Title");
        book.setPublication_year(2005);
        book.setAvailable(true);
        book.setAuthor(author);
        book.setGenres(genres);


        assertEquals("Title", book.getTitle());
        assertEquals(2005, book.getPublication_year());
        assertTrue(book.isAvailable());
        assertEquals(author, book.getAuthor());
        assertEquals(genres, book.getGenres());

        assertEquals("Ivan", book.getAuthor().getFirst_name());
        assertEquals("Ivanov", book.getAuthor().getLast_name());

        assertEquals("Fantasy", book.getGenres().iterator().next().getName());

        System.out.println("Test isBookWorking ended");


    }
}
