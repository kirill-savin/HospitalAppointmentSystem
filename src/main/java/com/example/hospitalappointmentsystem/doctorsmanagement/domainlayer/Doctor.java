package com.example.hospitalappointmentsystem.doctorsmanagement.domainlayer;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private DoctorId doctorId;

    @Embedded
    private FirstName firstName;

    @Embedded
    private LastName lastName;

    @Column(name="specialization")
    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    public Doctor(FirstName firstName, LastName lastName, Specialization specialization){
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
    }
}
