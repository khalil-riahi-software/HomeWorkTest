package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.Exception.SoldeInsuffisantMember;
import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.library.Library;

import java.util.HashSet;
import java.util.Set;

/**
 * A member is a person who can borrow and return books to a {@link Library}
 * A member can be either a student or a resident
 */
public abstract class Member {
    /**
     * late attribute to verif if member is autorized to borrow book or not
     */
    protected boolean late = false;

    /**
     * An initial sum of money the member has
     */
    protected float wallet;
    /**
     * All member should have collections's book initialized
     */
    private Set<Book> books = new HashSet<>();

    public Member(float wallet, boolean late) {
        this.wallet = wallet;
        this.late = late;
    }


    /**
     * The member should pay their books when they are returned to the library
     *
     * @param numberOfDays the number of days they kept the book
     */
    public abstract void payBook(int numberOfDays) throws SoldeInsuffisantMember;

    public void addBook(Book book) {
        this.books.add(book);
    }

    public float getWallet() {
        return wallet;
    }

    public boolean isLate() {
        return late;
    }

    public void setLate(boolean late) {
        this.late = late;
    }
}
