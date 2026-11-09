package com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer;

import java.util.UUID;

public class AuthorNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    private final UUID authorId;

    public AuthorNotFoundException(UUID authorId) {
        super("Author not found with id: " + authorId);
        this.authorId = authorId;
    }
    public AuthorNotFoundException(String message) {
        super(message);
        this.authorId = null;
    }

    public UUID getAuthorId() {
        return authorId;
    }
}
