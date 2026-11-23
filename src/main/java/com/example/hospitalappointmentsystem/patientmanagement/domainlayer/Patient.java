package com.example.hospitalappointmentsystem.patientmanagement.domainlayer;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private PatientId patientId;

    @Embedded
    private FirstName firstName;

    @Embedded
    private LastName lastName;

    @Embedded
    private PhoneNumber phoneNumber;

    public Patient(FirstName fn, LastName ln, PhoneNumber p){
        this.firstName = fn;
        this.lastName = ln;
        this.phoneNumber = p;
    }
}
