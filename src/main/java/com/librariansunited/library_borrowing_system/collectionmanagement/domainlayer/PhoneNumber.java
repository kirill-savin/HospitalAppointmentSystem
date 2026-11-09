package com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
// @Setter do not generate setters for Value Objects because they are immutable
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
// NoArgsConstructor is required by JPA, but it is protected so that no one can generate an invalid instance outside the persistence layer.
public class PhoneNumber {

    private static final String PHONE_PATTERN ="^\\+?[0-9 ()-]{7,15}$";

    @Column(name = "phone_number")
    private String value;
    public PhoneNumber(String value) {
        if (value == null || !value.matches(PHONE_PATTERN)) {
            throw new IllegalArgumentException( "Invalid phone number: " + value);
        }
        this.value = value;
    }
}
