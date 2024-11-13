package com.pover.Library.service;

import com.pover.Library.model.Author;
import com.pover.Library.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }


    public List<Author> findAll(){
        return authorRepository.findAll();
    }
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author not found with id " + id));
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }
    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = getAuthorById(id);
        author.setFirst_name(authorDetails.getFirst_name());
        author.setLast_name(authorDetails.getLast_name());
        author.setBirth_date(authorDetails.getBirth_date());
        return authorRepository.save(author);
    }
    public void deleteAuthor(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }
}
