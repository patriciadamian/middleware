package com.complany.middleware.controller;

import com.complany.middleware.domain.Book;
import com.complany.middleware.exception.ResourceNotFoundException;
import com.complany.middleware.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository repository;

    @PostMapping("/")
    public Book create(@Valid @RequestBody Book book) {
        return repository.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable(value = "id") Long bookId,
                       @Valid @RequestBody Book bookDetails) {

        Book book = repository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        book.setTitle(bookDetails.getTitle());
        book.setYear(bookDetails.getYear());
        book.setAuthors(bookDetails.getAuthors());

        return repository.save(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long bookId) {
        Book book = repository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        repository.delete(book);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{title}")
    public List<Book> findByTitle(@PathVariable(value = "title") String title) {
        return repository.findByTitle(title);
    }

    @GetMapping("/")
    public List<Book> getAll() {
        return repository.findAll();
    }
}
