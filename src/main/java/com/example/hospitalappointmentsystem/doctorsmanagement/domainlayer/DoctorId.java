package com.example.hospitalappointmentsystem.doctorsmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Embeddable
@Getter
@AllArgsConstructor
public class DoctorId {
    @Column(name = "doctor_id")
    private UUID doctorId;
    public DoctorId(){
        this.doctorId = UUID.randomUUID();
    }
}
