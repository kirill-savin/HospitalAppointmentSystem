package com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.time.Year;

@Embeddable
@Getter
// @Setter do not generate setters for Value Objects because they are immutable
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
// NoArgsConstructor is required by JPA, but it is protected so that no one can generate an invalid instance outside the persistence layer.
public class PublicationYear {

    @Column(name = "publication_year")// this sets the column name in the database table
    // we cannot use "year" because it is a reserved word in H2
    private final Integer year;

    public PublicationYear(Integer year) {
        if (year == null) {
            throw new IllegalArgumentException( "Publication year is required.");
        }
        int current = Year.now().getValue();
        if (year < 1450 || year > current) {
            throw new IllegalArgumentException(
                    "Publication year must be between 1450 and " + current + ", got: " + year);
        }
        this.year = year;
    }
}