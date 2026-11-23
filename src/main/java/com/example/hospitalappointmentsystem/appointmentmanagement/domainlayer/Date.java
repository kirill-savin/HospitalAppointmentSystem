package com.example.hospitalappointmentsystem.appointmentmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Date {
    @Column(name = "date", nullable = false)
    private LocalDate date;
    public Date(@NotNull @FutureOrPresent LocalDate date){
        this.date = date;
    }
}
