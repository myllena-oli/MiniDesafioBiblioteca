package org.example.model;

public class Author {
    private Long id;
    private String name;

    // Construtor vazio
    public Author() {
    }

    // Construtor com parâmetros
    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

