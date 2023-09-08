package org.example.dao;

import org.example.connection.DatabaseConnection;
import org.example.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookLoanDAO {

    Connection connection;

    public BookLoanDAO() {
        connection = DatabaseConnection.makingConnection();
    }


    public void insertLoan(Book book, String borrower) throws SQLException {
        String insertBorrowQuery = "INSERT INTO borrows (book_id, borrower) VALUES ((SELECT id FROM books WHERE title = ?), ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertBorrowQuery)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, borrower);
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

    public void listLoans() {
        String listLoansQuery = "SELECT books.title, authors.name, borrows.borrower FROM borrows JOIN books ON borrows.book_id = books.id" +
                " JOIN authors ON books.author = authors.id";

        try (PreparedStatement statement = connection.prepareStatement(listLoansQuery)) {
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Empréstimos:");
            while (resultSet.next()) {
                System.out.println("Título: " + resultSet.getString("title")
                        + ", Autor: " + resultSet.getString("name")
                        + ", Empréstimo para: "+ resultSet.getString("borrower"));
            }
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listBorrows(Connection connection) throws SQLException {
        String listBorrowsQuery = "SELECT b.title AS book_title, a.name AS author_name, borrows.borrower FROM borrows " +
                "INNER JOIN books b ON borrows.book_id = b.id " +
                "INNER JOIN authors a ON b.author_id = a.id";

        try (PreparedStatement statement = connection.prepareStatement(listBorrowsQuery)) {
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Empréstimos:");
            while (resultSet.next()) {
                System.out.println("Livro: " + resultSet.getString("book_title") + ", Autor: " + resultSet.getString("author_name") + ", Leitor: " + resultSet.getString("borrower"));
            }
            System.out.println();
        }
    }


    public void deleteBorrow(Book book, String borrower) {
        String deleteBorrowQuery = "DELETE FROM borrows WHERE book_id = (SELECT id FROM books WHERE title = ?) AND borrower = ?";

        try (PreparedStatement statement = connection.prepareStatement(deleteBorrowQuery)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, borrower);
            statement.executeUpdate();
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
