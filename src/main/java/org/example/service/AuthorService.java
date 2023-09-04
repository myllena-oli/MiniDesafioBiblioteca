package org.example.service;

import org.example.connection.DatabaseConnection;
import org.example.dao.AuthorDAO;
import org.example.model.Author;
import org.example.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AuthorService {

    AuthorDAO authorDAO;
    Connection connection;

    public AuthorService() {
        connection = DatabaseConnection.makingConnection();
    }



    public Author insertAuthor(String name) {


        for (Author author : listAuthors()) {
            if (author.getName().equals(name)) {
                return author;
            }
        }

        Author newAuthor = new Author();
        newAuthor.setName(name);

        authorDAO.insertAuthor(newAuthor);
        return newAuthor;
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


    public List<Author> listAuthors() {
        return authorDAO.listAuthors();

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
