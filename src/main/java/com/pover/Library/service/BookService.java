package com.pover.Library.service;

import com.pover.Library.dto.BookRequestDto;
import com.pover.Library.dto.BookResponseDto;
import com.pover.Library.model.Book;
import com.pover.Library.model.Genre;
import com.pover.Library.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDto addBook(@Valid BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setTitle(bookRequestDto.getTitle());
        book.setPublication_year(bookRequestDto.getPublication_year());
        book.setAvailable(bookRequestDto.isAvailable());

        Book savedBook = bookRepository.save(book);

        return convertToBookResponseDto(savedBook);
    }

    public List<BookResponseDto> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(this::convertToBookResponseDto)
                .collect(Collectors.toList());
    }

    public List<BookResponseDto> getBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return books.stream()
                .map(this::convertToBookResponseDto)
                .collect(Collectors.toList());
    }

    public BookResponseDto convertToBookResponseDto(Book book) {
        String authorFirstName = book.getAuthor() != null ? book.getAuthor().getFirst_name() : null;
        String authorLastName = book.getAuthor() != null ? book.getAuthor().getLast_name() : null;


        String genreNames = book.getGenres() != null ?
                book.getGenres().stream()
                        .map(Genre::getName)
                        .collect(Collectors.joining(", ")) : null;

        return new BookResponseDto(
                book.getBook_id(),
                book.getTitle(),
                book.getPublication_year(),
                book.isAvailable(),
                authorFirstName,
                authorLastName,
                genreNames
        );
    }
    public List<BookResponseDto> searchBooks(String query) {
        if ("alla".equalsIgnoreCase(query)) {
            return getBooks();
        }

        List<Book> books = bookRepository.searchBooks(query);
        return books.stream()
                .map(this::convertToBookResponseDto)
                .collect(Collectors.toList());
    }
}
