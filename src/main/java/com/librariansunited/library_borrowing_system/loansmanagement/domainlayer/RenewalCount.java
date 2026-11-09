package com.librariansunited.library_borrowing_system.loansmanagement.domainlayer;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class RenewalCount {
    private int count;
    private final int MAX_RENEWALS = 2;


    public RenewalCount increment(){
        count++;
        return this;
    }

    public boolean hasReachedLimit(){
        return count >= MAX_RENEWALS;
    }

    public boolean equals(Object other){
        return other.equals(this);
    }

    public int hashCode(){
        return 0;
    }


}
