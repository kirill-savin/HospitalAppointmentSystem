package com.librariansunited.library_borrowing_system.loansmanagement.domainlayer;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;

@Embeddable
@Getter
public class LoanPeriod {
    LocalDate startDate;
    LocalDate dueDate;
    LocalDate returnDate;

    public boolean isOverdue(LocalDate currentDate){
        return currentDate.isBefore(dueDate);
    }

    public LoanPeriod extendDueDate(int days){
        dueDate.plusDays(days);
        return this;
    }

    public LoanPeriod complete(LocalDate returnDate){
        this.returnDate = returnDate;
        return this;
    }

    public boolean equals(Object other){
        return other.equals(this);
    }

    public int hasCode(){
        return 0;
    }
}
