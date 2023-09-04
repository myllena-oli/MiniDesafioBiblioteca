package org.example;

import org.example.model.Author;
import org.example.model.Book;
import org.example.service.AuthorService;
import org.example.service.BookLoanService;
import org.example.service.BookService;


import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
    private BookService bookService;
    private AuthorService authorService;
    private BookLoanService bookLoanService;
    private Scanner scanner;

    public Application() {
        this.bookService = new BookService();
        this.authorService = new AuthorService();
        this.bookLoanService = new BookLoanService();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    public void run() {

        while (true) {
            showMenu();
            String input = scanner.nextLine();
            try {
                switch (input) {
                    case "1":
                        createBook();
                        break;
                    case "2":
                        createAuthor();
                        break;
                    case "3":
                        createLoan();
                        break;
                    case "4":
                        updateBook();
                        break;
                    case "5":
                        updateAuthor();
                        break;
                    case "6":
                        deleteBook();
                        break;
                    case "7":
                        listBooks();
                        break;
                    case "8":
                        listAuthor();
                        break;
                    case "9":
                        listLoans();
                        break;
                    case "0":
                        System.out.println("Saindo da aplicação.");
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um valor válido.");
                scanner.nextLine();
            }
        }

    }


    private void createBook() {

        System.out.print("Título do Livro: ");
        String tituloLivro = scanner.nextLine();

        System.out.print("Nome do Autor: ");
        String nomeAutor = scanner.nextLine();

        Author author = authorService.insertAuthor(nomeAutor);

        bookService.insertBook(tituloLivro, author);
    }

    private void createAuthor() {

        System.out.print("Nome do Autor: ");
        String nomeAutor = scanner.nextLine();

        Author author = authorService.insertAuthor(nomeAutor);

    }

    private void createLoan() {

        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        System.out.print("Digite para quem é o empréstimo: ");
        String name = scanner.nextLine();

        bookLoanService.insertLoan(title, name);

    }

    private void updateBook() {

        try {
            System.out.print("Digite o titulo do livro que deseja atualizar: ");
            String title = scanner.nextLine();
            scanner.nextLine();
            Book existingBook = new Book();

            for (Book book : bookService.listBooks()) {
                if (book.getTitle().equals(title)) {
                    existingBook = book;
                }
            }

            if (existingBook.getTitle() == null) {
                System.out.println("Livro não encontrado.");
                return;
            }

            System.out.print("Digite o novo nome do livro: ");
            String name = scanner.nextLine();

            bookService.updateBookTitle(existingBook, name);

            System.out.println("Livro atualizado com sucesso.");
        } catch (NumberFormatException e) {
            System.out.println("Livro inválido. Insira um livro válido.");
        }
    }

    private void updateAuthor() {

        try {
            System.out.print("Digite o nome do autor que deseja atualizar: ");
            String oldName = scanner.nextLine();
            scanner.nextLine();
            Author author = new Author();

            for (Author author1 : authorService.listAuthors()) {
                if (author1.getName().equals(oldName)) {
                    author = author1;
                }
            }

            if (author.getName() == null) {
                System.out.println("Autor não encontrado.");
                return;
            }

            System.out.print("Digite o novo nome do Autor: ");
            String name = scanner.nextLine();

            authorService.updateAuthorName(author, name);

            System.out.println("Autor atualizado com sucesso.");
        } catch (NumberFormatException e) {
            System.out.println("Autor inválido. Insira um Autor válido.");
        }
    }

    private void deleteBook() {

        System.out.print("Digite o título do livro que deseja deletar: ");
        String title = scanner.nextLine();

        Book existingBook = new Book();

        for (Book book : bookService.listBooks()) {
            if (book.getTitle().equals(title)) {
                existingBook = book;
            }
        }

        if (existingBook.getTitle() == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        bookService.deleteBook(title);
    }

    private void listBooks() {
        System.out.println(bookService.listBooks());
    }

    private void listAuthor() {
        System.out.println(authorService.listAuthors());
    }

    private void listLoans() {
        bookLoanService.listLoans();
    }


    private void showMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Inserir livro");
        System.out.println("2 - Inserir Autor");
        System.out.println("3 - Inserir impréstimo");
        System.out.println("4 - Atualizar livro");
        System.out.println("5 - Atualizar autor");
        System.out.println("6 - Atualizar emprestimo");
        System.out.println("7 - Excluir livro");
        System.out.println("8 - Listar livros");
        System.out.println("9 - Listar autores");
        System.out.println("10 - Listar emprestimos");
        System.out.println("0 - Sair");
    }

}