package com.librariansunited.library_borrowing_system.loansmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class MemberId {
    @Column(name="member_id")
    private UUID memberId;


    public MemberId generate(){
        memberId = UUID.randomUUID();
        return this;
    }

    public boolean equals(Object other){
        return other.equals(this);
    }

    public int hashCode(){
        return memberId.hashCode();
    }

}
