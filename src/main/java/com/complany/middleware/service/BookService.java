package com.complany.middleware.service;

import com.complany.middleware.domain.Book;
import com.complany.middleware.exception.ResourceNotFoundException;
import com.complany.middleware.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public Book create(Book book) {
        return repository.save(book);
    }

    public Book update(Long bookId, Book bookDetails) {

        Book book = repository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        book.setTitle(bookDetails.getTitle());
        book.setYear(bookDetails.getYear());
        book.setAuthors(bookDetails.getAuthors());

        return repository.save(book);
    }

    public void delete(Long bookId) {
        Book book = repository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", bookId));

        repository.delete(book);
    }

    public List<Book> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public List<Book> getAll() {
        return repository.findAll();
    }
}
