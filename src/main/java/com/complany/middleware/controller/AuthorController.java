package com.complany.middleware.controller;

import com.complany.middleware.domain.Author;
import com.complany.middleware.exception.ResourceNotFoundException;
import com.complany.middleware.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/")
    public Author create(@Valid @RequestBody Author author) {
        return authorService.create(author);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable(value = "id") Long authorId,
                       @Valid @RequestBody Author authorDetails) {
        return authorService.update(authorId, authorDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long authorId) {
        authorService.delete(authorId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{name}")
    public List<Author> findByName(@PathVariable(value = "name") String name) {
        return authorService.findByName(name);
    }

    @GetMapping("/")
    public List<Author> getAll() {
        return authorService.getAll();
    }
}
