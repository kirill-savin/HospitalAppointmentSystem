package com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
// @Setter do not generate setters for Value Objects because they are immutable
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
// NoArgsConstructor is required by JPA, but it is protected so that no one can generate an invalid instance outside the persistence layer.
public class ISBN {

    @Column(name = "isbn") // this sets the column name in the database table
    // we cannot use "value" because it is a reserved word in H2
    private final String value;

    public ISBN(String value) {
        this.value = validate(value);
    }

    private static String validate(String candidate) {
        if (candidate == null || candidate.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be blank.");
        }
        String digits = candidate.replace("-", "");
        int sum = 0;
        for (int i = 0; i < 13; i++) {
            int digit = digits.charAt(i) - '0';
            sum += (i % 2 == 0) ? digit : digit * 3;
        }
        if (sum % 10 != 0) {
            throw new IllegalArgumentException(
                    "ISBN-13 checksum failed: " + candidate);
        }
        return digits;   // normalized form: always 13 digits, no hyphens
    }
}
