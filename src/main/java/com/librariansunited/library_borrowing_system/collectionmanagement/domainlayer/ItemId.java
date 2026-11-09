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
public class ItemId {
    @Column(name="item_id", unique = true, nullable = false, updatable = false)
    private UUID value;

    public ItemId(UUID value){
        if (value == null){
            throw new IllegalArgumentException("Item id is required");
        }
        this.value = value;
    }

    public static ItemId generate(){
        return new ItemId(UUID.randomUUID());
    }
}
