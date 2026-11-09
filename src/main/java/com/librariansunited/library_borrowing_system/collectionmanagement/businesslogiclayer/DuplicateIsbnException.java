package com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer;

public class DuplicateIsbnException extends Exception {

    public DuplicateIsbnException(String isbn) {
        super("A book with ISBN " + isbn + " already exists.");
    }
}
