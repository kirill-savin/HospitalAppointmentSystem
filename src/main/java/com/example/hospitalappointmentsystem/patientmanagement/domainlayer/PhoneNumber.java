package com.example.hospitalappointmentsystem.patientmanagement.domainlayer;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class PhoneNumber {
    private static final String PHONE_PATTERN ="^\\+?[0-9 ()-]{7,15}$";

    @Column(name = "phone_number")
    private String value;
    public PhoneNumber(String value) {
        if (value == null || !value.matches(PHONE_PATTERN)) {
            throw new IllegalArgumentException( "Invalid phone number: " + value);
        }
        this.value = value;
    }
}
