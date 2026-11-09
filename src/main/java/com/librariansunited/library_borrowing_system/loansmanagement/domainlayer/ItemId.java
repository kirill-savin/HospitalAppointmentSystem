package com.librariansunited.library_borrowing_system.loansmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class ItemId {

    @Column(name="item_id")
    private UUID itemId;

    public ItemId generate(){
        itemId = UUID.randomUUID();
        return this;
    }

    public boolean equals(Object other){
        return other.equals(this);
    }

    public int hashCode(){
        return itemId.hashCode();
    }
}
