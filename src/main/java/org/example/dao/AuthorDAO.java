package org.example.dao;

import org.example.connection.DatabaseConnection;
import org.example.model.Author;
import org.example.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {

    Connection connection;

    public AuthorDAO() {
        connection = DatabaseConnection.makingConnection();
    }

    public void insertAuthor(Author author) {
        String insertAuthorQuery = "INSERT INTO authors (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(insertAuthorQuery)) {
            statement.setString(1, author.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAuthorName(Author author, String newName) {
        String updateBookQuery = "UPDATE authors SET name = ? WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(updateBookQuery)) {
            statement.setString(1, newName);
            statement.setString(2, author.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //    public void listAuthors() {
//        String listAuthorsQuery = "SELECT * FROM authors";
//
//        try (PreparedStatement statement = connection.prepareStatement(listAuthorsQuery)) {
//            ResultSet resultSet = statement.executeQuery();
//            System.out.println("Autores:");
//            while (resultSet.next()) {
//                System.out.println("ID: " + resultSet.getInt("id") + ", Nome: " + resultSet.getString("name"));
//            }
//            System.out.println();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public List<Author> listAuthors() {
        List<Author> authors = new ArrayList<>();
        String listAuthorsQuery = "SELECT * FROM authors";

        try (PreparedStatement statement = connection.prepareStatement(listAuthorsQuery)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getLong("id"));
                author.setName(resultSet.getString("name"));
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }


    public void deleteAuthor(Author author) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books WHERE name = ?")) {
            preparedStatement.setString(1, author.getName());
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
