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
public class Duration {

    @Column(name = "duration")
    private final Integer minutes;

    public Duration(Integer minutes) {
        if (minutes == null) {
            throw new IllegalArgumentException("Duration is required.");
        }
        if (minutes <= 0) {
            throw new IllegalArgumentException("Duration must be a positive number of minutes, got: "+ minutes);
        }
        this.minutes = minutes;
    }
}
