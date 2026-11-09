package com.librariansunited.library_borrowing_system.collectionmanagement.infrastructurelayer;

import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository <Book, Integer> {

    // Derived path: isbn (embedded) -> value -> column isbn
    // Used by the duplicate-ISBN business rule.
    boolean existsByIsbnValue(String isbn);

    // Handy for future lookups such as "find the book with this ISBN".
    Optional findByIsbnValue(String isbn);
}