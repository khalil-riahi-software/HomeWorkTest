package fr.d2factory.libraryapp.library;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.book.ISBN;
import fr.d2factory.libraryapp.util.BookBuilder;
import fr.d2factory.libraryapp.util.ISBNBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BookRepositoryTest {


    private static Book book1=new BookBuilder().withAuthor("Victor Hugo")
                .withIsbn(new ISBNBuilder().withIsbnCode(2103256L).build()).withTitle("Les miserables").build();
    private static Book book2=new BookBuilder().withAuthor("Victor Hugo")
            .withIsbn(new ISBNBuilder().withIsbnCode(14787453L).build()).withTitle("Le dernier jour d'un condamné").build();

    private static Book book3=new BookBuilder().withAuthor("Miguel Ruiz")
            .withIsbn(new ISBNBuilder().withIsbnCode(1452783256L).build()).withTitle(" La voie de la liberté personnelle").build();

    private Map<ISBN, Book> availableBooks = new HashMap<>();
    private Map<Book, LocalDate> borrowedBooks = new HashMap<>();

    private BookRepository bookRepository=new BookRepository();



    @Before
    public void setup(){

        availableBooks.put(book1.getIsbn(),book1);
        availableBooks.put(book2.getIsbn(),book2);
        availableBooks.put(book3.getIsbn(),book3);
        borrowedBooks.put(book1,LocalDate.of(2018,04,3));

    }


    @Test
    public void should_add_book_List_in_available_book(){
        Map<ISBN, Book> result=bookRepository.addBooks(Arrays.asList(book1,book2,book3));
        Assert.assertEquals(result.size(),availableBooks.size());
        Assert.assertTrue(result.containsKey(book1.getIsbn()));
        Assert.assertTrue(result.containsValue(book1));
        Assert.assertEquals(result.keySet().size(),3);
        Assert.assertEquals(result.values().size(),3);

    }

    @Test
    public void should_find_book_when_isbn_is_valid(){
        bookRepository.addBooks(Arrays.asList(book1,book2,book3));
        Book result=bookRepository.findBook(book1.getIsbn().getIsbnCode());
        Assert.assertEquals(result.getIsbn().getIsbnCode(),book1.getIsbn().getIsbnCode());
        Assert.assertTrue(result.getAuthor().equals(book1.getAuthor()));
        Assert.assertTrue(result.getTitle().equals(book1.getTitle()));


    }

    @Test
    public void should_not_find_book_when_isbn_is_not_valid(){
        bookRepository.addBooks(Arrays.asList(book1,book2,book3));
        Book result=bookRepository.findBook(147852L);
        Assert.assertNull(result);


    }


    @Test
    public void should_save_book_borrow_when_Date_is_valid(){
       Map<Book,LocalDate>result= bookRepository.saveBookBorrow(book1,LocalDate.of(2018,04,3));
        Assert.assertNotNull(result);
        Assert.assertEquals(borrowedBooks.size(),result.size());
        Assert.assertTrue(result.containsKey(book1));
        Assert.assertTrue(result.containsValue(LocalDate.of(2018,04,3)));
    }


    @Test
    public void should_find_borrowed_book_when_date_is_valid(){
        bookRepository.saveBookBorrow(book1,LocalDate.of(2018,04,3));
       LocalDate dateBorrowed= bookRepository.findBorrowedBookDate(book1);
       Assert.assertEquals(dateBorrowed,LocalDate.of(2018,04,3));

    }

    @After
    public void close(){
        availableBooks.clear();
        borrowedBooks.clear();

    }
}






