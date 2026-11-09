package com.librariansunited.library_borrowing_system.loansmanagement.domainlayer;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
public class Money {
    private BigDecimal amount;
    private String currency;

    public Money add(Money other){
        amount.add(other.amount);
        return this;
    }

    public Money multiply(int factor){
        amount.multiply(new BigDecimal(factor));
        return this;
    }

    public boolean isZero(){
        return amount.equals(0);
    }

    public boolean isNegative(){
        return amount.intValue() < 0;
    }

    public boolean equals(Object other){
        return other.equals(this);
    }

    public int hasCode(){
        return 0;
    }


}
