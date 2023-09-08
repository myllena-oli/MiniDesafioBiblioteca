package org.example.service;

import org.example.connection.DatabaseConnection;
import org.example.dao.BookDAO;
import org.example.model.Author;
import org.example.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookService {

    BookDAO bookDAO;
    Connection connection;

    public BookService() {
        connection = DatabaseConnection.makingConnection();
        bookDAO = new BookDAO();
    }

    public void insertBook(String title, Author author) {

        for (Book book : listBooks()) {
            if (book.getTitle().equals(title)) {
                System.out.println("\nLivro já cadastrado!\n");
                return;
            }
        }

        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);

        bookDAO.insertBook(newBook);
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

    public List<Book> listBooks() {
        return bookDAO.listBooks();
    }

    public void deleteBook(String title) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books WHERE title = ?")) {
            preparedStatement.setString(1, title);
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
