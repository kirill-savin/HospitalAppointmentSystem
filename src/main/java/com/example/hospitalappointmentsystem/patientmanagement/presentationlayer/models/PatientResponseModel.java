package com.example.hospitalappointmentsystem.patientmanagement.presentationlayer.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseModel {
    private UUID patientId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
