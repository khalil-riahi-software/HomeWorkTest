package fr.d2factory.libraryapp.library;

import fr.d2factory.libraryapp.Exception.HasLateBooksException;
import fr.d2factory.libraryapp.Exception.MemberException;
import fr.d2factory.libraryapp.Exception.SoldeInsuffisantMember;
import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.member.Resident;
import fr.d2factory.libraryapp.member.Student;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class LibraryImplement implements Library {
    private static String Exception_MEMBER_LATE_TO_BORROW = "You must return all books borrowed before";
    private static String Exception_BOOK_LATE_TO_BORROW = "Sorry, this is not available at the moment";

    private BookRepository bookRepository = new BookRepository();

    @Override
    public Optional<Book> borrowBook(long isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException,MemberException {
        Optional<Book> book = Optional.empty();
        if (member instanceof Student || member instanceof Resident) {
            if (member.isLate()) throw new HasLateBooksException(Exception_BOOK_LATE_TO_BORROW);
            Book bookToFind = bookRepository.findBook(isbnCode);

            if (bookToFind != null) {
                bookRepository.saveBookBorrow(bookToFind, borrowedAt);
                member.addBook(bookToFind);
                book = Optional.of(bookToFind);
            }else{
                throw new MemberException(Exception_MEMBER_LATE_TO_BORROW);
            }

        }
        return book;
    }


    @Override
    public void returnBook(Book book, Member member) throws SoldeInsuffisantMember {
        LocalDate dateTOBorrowBook = bookRepository.findBorrowedBookDate(book);
        Period period = Period.between(dateTOBorrowBook, LocalDate.now());
        int numberOfDays = period.getDays();
        member.payBook(numberOfDays);
        if (member instanceof Resident) {
            if (numberOfDays > 60) {
                member.setLate(true);
            }
        } else {
            if (numberOfDays > 30) {
                member.setLate(true);
            }
        }


    }


}
