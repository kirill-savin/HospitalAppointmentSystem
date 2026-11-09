package com.librariansunited.library_borrowing_system.loansmanagement.domainlayer;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="loan")
public class Loan {
    @Id
    private LoanId loanId;

    private MemberId borrowerId;

    private ItemId itemId;

    private LoanPeriod loanPeriod;

    private RenewalCount renewalCount;

    private Money accuredFee;

    private LoanStatus status;

    public boolean canRenew(){
        return renewalCount.hasReachedLimit();
    }

    public void renew(int daysToExtend){
        loanPeriod.extendDueDate(daysToExtend);
        status = LoanStatus.ACTIVE;
    }

    public void returnItem(LocalDate returnDate){
        loanPeriod.complete(returnDate);
    }

    public Money calulateOverdueFee(Money dailyRate, LocalDate currentDate){
        accuredFee.add(new Money( BigDecimal.valueOf(dailyRate.getAmount().intValue()  * BigDecimal.valueOf(loanPeriod.startDate.until(currentDate, ChronoUnit.DAYS)).intValue()),accuredFee.getCurrency()));
        return accuredFee;
    }


    public void markOverdue(LocalDate date){
        if(loanPeriod.isOverdue(date)){
            status = LoanStatus.OVERDUE;
        }
    }
}
