package com.complany.middleware.controller;

import com.complany.middleware.domain.Author;
import com.complany.middleware.exception.ResourceNotFoundException;
import com.complany.middleware.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorRepository repository;

    @PostMapping("/")
    public Author create(@Valid @RequestBody Author author) {
        return repository.save(author);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable(value = "id") Long authorId,
                       @Valid @RequestBody Author authorDetails) {

        Author author = repository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));

        author.setName(authorDetails.getName());

        return repository.save(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long authorId) {
        Author author = repository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", authorId));

        repository.delete(author);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{name}")
    public List<Author> findByName(@PathVariable(value = "name") String name) {
        return repository.findByName(name);
    }

    @GetMapping("/")
    public List<Author> getAll() {
        return repository.findAll();
    }
}
