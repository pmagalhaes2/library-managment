package library_managment;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class LibraryManagment {
    ArrayList<Book> books = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        LibraryManagment libraryManagment = new LibraryManagment();
        libraryManagment.init();
    }

    public void init() {
        int option;
        Print printMenu = new Print();

        do {
            printMenu.printMenu();

            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Tente novamente!");
                option = -1;
            }

            switch (option) {
                case 0:
                    sc.close();
                    break;
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String title = sc.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String author = sc.nextLine();
                    UUID id = UUID.randomUUID();

                    addBook(new Book(id, title, author));
                    break;
                case 2:
                    System.out.print("Digite o título do livro: ");
                    title = sc.nextLine();
                    removeBookByTitle(title);
                    break;
                case 3:
                    getAllBooks();
                    break;
                case 4:
                    System.out.print("Digite o título do livro: ");
                    title = sc.nextLine();
                    getBookByTitle(title);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente!");
            }

        } while (option != 0);
    }

    public void addBook(Book book) {
        try {
            books.add(book);
            System.out.printf("Livro '%s' adicionado com sucesso!", book.getTitle());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao adicionar o livro: " + e.getMessage());
        }
    }

    public void removeBookByTitle(String bookName) {
        try {
            Book foundedBook = books.stream().filter(book -> book.getTitle().equals(bookName)).findFirst().get();
            books.remove(foundedBook);
            System.out.printf("Livro '%s' removido com sucesso!%n", foundedBook.getTitle());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao remover o livro: " + e.getMessage());
        }
    }

    public void getAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Não existem livros cadastrados!");
        } else {
            System.out.println("Livros cadastrados: ");
            for (Book book : books) {
                System.out.println(book.getTitle());
            }
        }
    }

    public void getBookByTitle(String bookName) {
        try {
            Book foundedBook = books.stream().filter(book -> book.getTitle().equals(bookName)).findFirst().get();
            System.out.printf("Título: %s - Autor: %s", foundedBook.getTitle(), foundedBook.getAuthor());
            System.out.println();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao localizar o livro: " + e.getMessage());
        }
    }
}
