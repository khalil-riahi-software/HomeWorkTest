package fr.d2factory.libraryapp.util;

import fr.d2factory.libraryapp.book.ISBN;

public class ISBNBuilder {

    private Long isbnCode;

public ISBNBuilder(){}

    public ISBNBuilder withIsbnCode(Long isbnCode){
        this.isbnCode=isbnCode;
        return this;
    }

    public ISBN build() {
        return new ISBN(isbnCode);
    }
}
