package fr.d2factory.libraryapp.util;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.ISBN;

public class BookBuilder {

    private  String title;
    private  String author;
    private ISBN isbn;

    public BookBuilder withTitle(String title){
        this.title=title;
        return this;
    }
    public BookBuilder withAuthor(String author){
        this.author=author;
        return this;
    }

    public BookBuilder withIsbn(ISBN isbn){
        this.isbn=isbn;
        return this;
    }
    public Book build(){
        return new Book(title,author,isbn);
    }
}