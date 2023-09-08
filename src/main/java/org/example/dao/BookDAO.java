package org.example.dao;

import org.example.connection.DatabaseConnection;
import org.example.model.Author;
import org.example.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    Connection connection;
    AuthorDAO authorDAO;

    public BookDAO() {
        connection = DatabaseConnection.makingConnection();
        authorDAO = new AuthorDAO();
    }

    public void insertBook(Book book) {
        String insertBookQuery = "INSERT INTO books (title, author) VALUES (?, (SELECT id FROM authors WHERE name = ?))";

        try (PreparedStatement statement = connection.prepareStatement(insertBookQuery)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor().getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBookTitle(Book book, String newTitle) {
        String updateBookQuery = "UPDATE books SET title = ? WHERE title = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateBookQuery)) {
            statement.setString(1, newTitle);
            statement.setString(2, book.getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void listBooks() {
//        String listBooksQuery = "SELECT books.title, authors.name FROM books JOIN authors ON books.author_id = authors.id";
//
//        try (PreparedStatement statement = connection.prepareStatement(listBooksQuery)) {
//            ResultSet resultSet = statement.executeQuery();
//            System.out.println("Livros:");
//            while (resultSet.next()) {
//                System.out.println("Título: " + resultSet.getString("title") + ", Autor: " + resultSet.getString("name"));
//            }
//            System.out.println();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public List<Book> listBooks() {
        List<Book> books = new ArrayList<>();
        //String listBooksQuery = "SELECT * FROM books";
        //String listBooksQuery = "SELECT b.id, b.title, a.name AS author_name FROM books b JOIN authors a ON b.author = a.id";
        String listBooksQuery = "SELECT books.id, books.title, authors.name FROM books JOIN authors ON books.author = authors.id";


        try (PreparedStatement statement = connection.prepareStatement(listBooksQuery)) {
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setTitle(resultSet.getString("title"));
                String name = resultSet.getString("name");
                Author author = getSpecificAuthor(name);
                book.setAuthor(author);
                books.add(book);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;

    }

    public Author getSpecificAuthor(String name){
        for (Author author : authorDAO.listAuthors()) {
            if (author.getName().equals(name)) {
                return author;
            }
        }
        return null;
    }


    public void deleteBook(Book book) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books WHERE title = ?")) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    public List<Book> getAllBooks() {
//        List<Book> books = new ArrayList<>();
//        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery("SELECT * FROM books")) {
//            while (resultSet.next()) {
//                Book book = new Book();
//                book.setId(resultSet.getLong("id"));
//                book.setName(resultSet.getString("name"));
//                book.setAuthor(resultSet.getString("author"));
//                book.setReleaseDate(resultSet.getDate("release_date").toLocalDate());
//
//                books.add(book);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return books;
//    }
}
