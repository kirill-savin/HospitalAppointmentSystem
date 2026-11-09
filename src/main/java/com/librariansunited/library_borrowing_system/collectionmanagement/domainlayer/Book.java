package com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("BOOK")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends LibraryItem {

    // ISBN is an @Embeddable: it maps its own value into the
    // isbn column of collection_items, so no @Column here.
    @Embedded
    @Getter(AccessLevel.NONE)
    private ISBN isbn;

    // FK: authors.item_id -> collection_items.id (surrogate PK),
    // matching the seed data's item_id values 1, 2, 3.
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "item_id")  // the FK column in the authors table
    @Getter(AccessLevel.NONE)    // suppress the Lombok getter so we can hand-write it
    private List<Author> authors = new ArrayList<>();

    public Book(ItemId itemId, String title,
                PublicationYear publicationYear, ISBN isbn) {
        super(itemId, title, publicationYear);
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN is required.");
        }
        this.isbn = isbn;
    }

    public Book(String title,
                PublicationYear publicationYear, ISBN isbn) {
        super(new ItemId(), title, publicationYear);
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN is required.");
        }
        this.isbn = isbn;
    }

    // Hand-written accessors: getISBN() matches the design and the
    // call sites; getAuthors() hands out an unmodifiable copy.
    public ISBN getISBN() { return isbn; }

    public List<Author> getAuthors() { return List.copyOf(authors); }

    public void addAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null.");
        }
        if (hasAuthor(author.getAuthorId().getValue())) {
            throw new IllegalStateException(
                    "Author " + author.getAuthorId().getValue()
                            + " is already linked to this book.");
        }
        authors.add(author);
    }

    // Removal is implemented by identifier, not by equals(): entities
    // do not have value equality, and this works regardless of which
    // Author instance the caller holds.
    public void removeAuthor(Author author) {
        if (author == null) {
            return;
        }
        UUID authorId = author.getAuthorId().getValue();
        authors.removeIf(a -> a.getAuthorId().getValue().equals(authorId));
    }

    public boolean hasAuthor(UUID authorId) {
        return authors.stream()
                .anyMatch(a -> a.getAuthorId().getValue().equals(authorId));
    }

    @Override
    public String getDescription() {
        return "Book: " + getTitle() + " (ISBN "
                + (isbn == null ? "n/a" : isbn.getValue()) + ")";
    }
}
