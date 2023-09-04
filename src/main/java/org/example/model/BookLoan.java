package org.example.model;

import org.example.model.Book;

public class BookLoan {
    private int id;
    private Book book;
    private String borrower;

    // Construtor vazio
    public BookLoan() {
    }

    // Construtor com parâmetros
    public BookLoan(int id, Book book, String borrower) {
        this.id = id;
        this.book = book;
        this.borrower = borrower;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }
}

