package com.complany.middleware.controller;

import com.complany.middleware.domain.Book;
import com.complany.middleware.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public Book create(@Valid @RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable(value = "id") Long bookId,
                       @Valid @RequestBody Book bookDetails) {
        return bookService.update(bookId, bookDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long bookId) {
        bookService.delete(bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{title}")
    public List<Book> findByTitle(@PathVariable(value = "title") String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping("/")
    public List<Book> getAll() {
        return bookService.getAll();
    }
}
