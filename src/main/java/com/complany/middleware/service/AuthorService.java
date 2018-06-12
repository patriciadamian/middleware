package com.complany.middleware.service;

import com.complany.middleware.domain.Author;
import com.complany.middleware.exception.ResourceNotFoundException;
import com.complany.middleware.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private AuthorRepository repository;

    public Author create(Author author) {
        return repository.save(author);
    }

    public Author update(Long authorId, Author authorDetails) {

        Author author = repository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));

        author.setName(authorDetails.getName());

        return repository.save(author);
    }

    public void delete(Long authorId) {
        Author author = repository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));

        repository.delete(author);
    }

    public List<Author> findByName(String name) {
        return repository.findByName(name);
    }

    public List<Author> getAll() {
        return repository.findAll();
    }
}
