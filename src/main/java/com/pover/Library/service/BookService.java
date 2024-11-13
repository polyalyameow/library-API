package com.pover.Library.service;

import com.pover.Library.dto.BookRequestDto;
import com.pover.Library.dto.BookResponseDto;
import com.pover.Library.model.Book;
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

        return new BookResponseDto(
                savedBook.getBook_id(),
                savedBook.getTitle(),
                savedBook.getPublication_year(),
                savedBook.isAvailable()
        );
    }

    public List<BookResponseDto> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> new BookResponseDto(
                        book.getBook_id(),
                        book.getTitle(),
                        book.getPublication_year(),
                        book.isAvailable()
                ))
                .collect(Collectors.toList());
    }

    public List<BookResponseDto> getBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return books.stream()
                .map(book -> new BookResponseDto(
                        book.getBook_id(),
                        book.getTitle(),
                        book.getPublication_year(),
                        book.isAvailable()
                ))
                .collect(Collectors.toList());
    }
}
