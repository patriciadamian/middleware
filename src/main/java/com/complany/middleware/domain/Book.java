package com.complany.middleware.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private int year;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "list_of_books",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = { @JoinColumn(name = "author_id")}
    )
    private Set<Author> authors = new HashSet<>();

    public Book(Long book_id, String title, int year, Set<Author> authors) {
        this.book_id = book_id;
        this.title = title;
        this.year = year;
        this.authors = authors;
    }

    public Book() {
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
