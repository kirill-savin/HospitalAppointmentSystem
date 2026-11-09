package com.librariansunited.library_borrowing_system.collectionmanagement.infrastructurelayer;

import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository <Author, Integer> {
    // findById, findAll, existsById and deleteById are inherited.
    // Derived path: authorId (embedded) -> value -> column author_id
    Optional findByAuthorIdValue(UUID value);

    boolean existsByAuthorIdValue(UUID value);

    // Optional: search inside the @ElementCollection of Value Objects.
    List<Author> findByPhoneNumbersValue(String phoneNumber);
}
