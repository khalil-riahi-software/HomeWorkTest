package fr.d2factory.libraryapp.library;

import fr.d2factory.libraryapp.Exception.HasLateBooksException;
import fr.d2factory.libraryapp.Exception.MemberException;
import fr.d2factory.libraryapp.Exception.SoldeInsuffisantMember;
import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.book.ISBN;
import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.util.ResidentBuilder;
import fr.d2factory.libraryapp.util.StudentBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class LibraryTest {
    private static String URL_BOOK = "src/test/resources/books.json";
    private ArrayList<Book> books;
    private Library library;
    private BookRepository bookRepository;

    @Before
    public void setup() throws Exception {
        parseFileToJsonArray(URL_BOOK);
        this.library = new LibraryImplement();
        this.bookRepository = new BookRepository();
        if (this.books != null && !this.books.isEmpty()) {
            this.bookRepository.addBooks(books);
        }
    }

    @Test
    public void member_can_borrow_a_book_if_book_is_available() throws HasLateBooksException, MemberException {
        Member member = new StudentBuilder().withLevelStudy(2).withWallet(200).islate(false).build();
        Optional<Book> result = library.borrowBook(books.get(0).getIsbn().getIsbnCode(), member, LocalDate.of(2019, 04, 03));
        Assert.assertNotNull(result.get());
        Assert.assertEquals(books.get(0).getIsbn().toString(), result.get().getIsbn().toString());
        Assert.assertTrue(bookRepository.getBorrowedBooks().size() > 0);


    }

    @Test
    public void borrowed_book_is_no_longer_available() throws HasLateBooksException, MemberException  {
        bookRepository.saveBookBorrow(books.get(0), LocalDate.of(2019, 04, 03));
        Member member = new StudentBuilder().withLevelStudy(3).withWallet(400f).islate(false).build();
        Optional<Book> result = library.borrowBook(books.get(0).getIsbn().getIsbnCode(), member, LocalDate.of(2019, 04, 03));
        Assert.assertNotNull(result);
        Assert.assertTrue(bookRepository.getBorrowedBooks().containsKey(books.get(0)));
    }

    @Test
    @Ignore
    public void residents_are_taxed_10cents_for_each_day_they_keep_a_book() throws HasLateBooksException, SoldeInsuffisantMember, MemberException  {
        Member member = new ResidentBuilder().withWallet(400).islate(false).build();
        Period periode = Period.between(LocalDate.of(2019, 04, 03), LocalDate.now());
        float tarif = (10 / 100) * periode.getDays();
        library.borrowBook(books.get(0).getIsbn().getIsbnCode(), member, LocalDate.of(2019, 04, 03));
        library.returnBook(books.get(0), member);
        Assert.assertTrue(member.getWallet() <= 400f);


    }

    @Test
    @Ignore

    public void students_pay_10_cents_the_first_30days() {
    }

    @Test
    @Ignore

    public void students_in_1st_year_are_not_taxed_for_the_first_15days() {
    }

    @Test
    @Ignore

    public void students_pay_15cents_for_each_day_they_keep_a_book_after_the_initial_30days() {
    }

    @Test
    @Ignore

    public void residents_pay_20cents_for_each_day_they_keep_a_book_after_the_initial_60days() {
    }

    @Test
    @Ignore

    public void members_cannot_borrow_book_if_they_have_late_books() {
    }

    private void parseFileToJsonArray(String url) throws Exception {

        if (url != null && !url.isEmpty()) {
            FileReader reader = new FileReader(url);
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
            this.books = new ArrayList<>();
            Iterator iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject jsonObject = (JSONObject) iterator.next();
                Book book = new Book();
                ISBN isbn = new ISBN();
                book.setAuthor((String) jsonObject.get("author"));
                book.setTitle((String) jsonObject.get("title"));
                JSONObject jsonObject2 = (JSONObject) ((JSONObject) jsonArray.iterator().next()).get("isbn");
                String isbnCode = (String) "" + jsonObject2.get("isbnCode");
                isbn.setIsbnCode(Long.valueOf(isbnCode));
                book.setIsbn(isbn);
                this.books.add(book);
            }

        }
    }

}

