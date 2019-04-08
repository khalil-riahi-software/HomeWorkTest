package fr.d2factory.libraryapp.library;

import fr.d2factory.libraryapp.Exception.HasLateBooksException;
import fr.d2factory.libraryapp.Exception.MemberException;
import fr.d2factory.libraryapp.Exception.SoldeInsuffisantMember;
import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.member.Member;

import java.time.LocalDate;
import java.util.Optional;

/**
 * The library class is in charge of stocking the books and managing the return delays and members
 * <p>
 * The books are available via the {@link fr.d2factory.libraryapp.book.BookRepository}
 */
public interface Library {

    /**
     * A member is borrowing a book from our library.
     *
     * @param isbnCode   the isbn code of the book
     * @param member     the member who is borrowing the book
     * @param borrowedAt the date when the book was borrowed
     * @return the book the member wishes to obtain if found
     * @throws HasLateBooksException in case the member has books that are late
     * @see fr.d2factory.libraryapp.book.ISBN
     * @see Member
     */
    Optional<Book> borrowBook(long isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException, MemberException;

    /**
     * A member returns a book to the library.
     * We should calculate the tarif and probably charge the member
     *
     * @param book   the {@link Book} they return
     * @param member the {@link Member} who is returning the book
     * @see Member#payBook(int)
     */
    void returnBook(Book book, Member member) throws SoldeInsuffisantMember;
}
