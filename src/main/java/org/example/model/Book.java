package org.example.model;

import org.example.model.Author;

public class Book {
    private Long id;
    private String title;
    private Author author;

    // Construtor vazio
    public Book() {
    }

    // Construtor com parâmetros
    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

