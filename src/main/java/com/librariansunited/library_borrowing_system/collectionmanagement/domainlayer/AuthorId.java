package com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.UUID;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class AuthorId {
    @Column(name="author_id", unique = true, nullable = false, updatable = false)
    private UUID value;

    public AuthorId(UUID value){
        if (value == null){
            throw new IllegalArgumentException("Author id is required");
        }
        this.value = value;
    }

    public static AuthorId generate(){
        return new AuthorId(UUID.randomUUID());
    }
}
