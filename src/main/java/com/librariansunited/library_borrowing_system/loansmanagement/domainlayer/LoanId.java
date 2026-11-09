package com.librariansunited.library_borrowing_system.loansmanagement.domainlayer;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

import java.util.UUID;

@Embeddable
public class LoanId {
    @Column(name="loan_id")
    private UUID loanId;

    public LoanId generate(){
        loanId = UUID.randomUUID();
        return this;
    }

    public boolean equals(Object other){
        return other.equals(this);
    }

    public int hashCode(){
        return loanId.hashCode();
    }

}
