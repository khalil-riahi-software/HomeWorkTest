package fr.d2factory.libraryapp.book;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository {
    private static Map<ISBN, Book> availableBooks = new HashMap<>();
    private static Map<Book, LocalDate> borrowedBooks = new HashMap<>();

    public BookRepository() {
    }

    public static Map<ISBN, Book> addBooks(List<Book> books) {
        if (books != null && !books.isEmpty()) {

            books.stream().forEach(book -> {
                if (book != null) {
                    availableBooks.put(book.getIsbn(), book);
                }
            });
        }

        return availableBooks;


    }

    public static Map<Book, LocalDate> saveBookBorrow(Book book, LocalDate borrowedAt) {
        if (book != null && borrowedAt != null) {
            borrowedBooks.put(book, borrowedAt);
        }
        return borrowedBooks;
    }

    public Book findBook(long isbnCode) {
        return this.availableBooks.entrySet().stream().filter(v -> v.getKey().getIsbnCode() ==
                isbnCode).map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);

    }

    public LocalDate findBorrowedBookDate(Book book) {
        return this.borrowedBooks.entrySet().stream().filter(b -> b.getKey().equals(book)).map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);

    }

    public Map<Book, LocalDate> getBorrowedBooks() {
        return borrowedBooks;
    }
}
