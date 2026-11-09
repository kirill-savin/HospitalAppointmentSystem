package com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Author {

    // Internal surrogate key. Persistence-only: never exposed through
    // the API, never placed in a DTO. @GeneratedValue + IDENTITY.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)            // the annotation the comment promised
    private int id;

    // External, domain-based identifier; the one the API speaks.
    @Embedded
    private AuthorId authorId;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(
            name = "author_phone_numbers",
            joinColumns = @JoinColumn(name = "author_id"))
    @Column(name = "phone_number")
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    public Author(AuthorId authorId, String name, Address address) {
        if (authorId == null) {
            throw new IllegalArgumentException("Author id is required.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (address == null) {
            throw new IllegalArgumentException("Address is required.");
        }
        this.authorId = authorId;
        this.name = name;
        this.address = address;
    }
    public Author( String name, Address address) {
        this(AuthorId.generate() , name , address);
    }

    public void addPhoneNumber(PhoneNumber phone) {
        if (phone == null) {
            throw new IllegalArgumentException(
                    "Phone number cannot be null.");
        }
        if (phoneNumbers.contains(phone)) {   // value equality via PhoneNumber
            throw new IllegalStateException(
                    "This phone number is already registered.");
        }
        phoneNumbers.add(phone);
    }

    public void removePhoneNumber(PhoneNumber phone) {
        phoneNumbers.remove(phone);
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return List.copyOf(phoneNumbers);   // Lombok defers to this method
    }
}
