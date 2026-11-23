package com.example.hospitalappointmentsystem.patientmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class PatientId {
    @Column(name = "patient_id")
    private UUID patientId;
    public PatientId(){
        this.patientId = UUID.randomUUID();
    }
}

