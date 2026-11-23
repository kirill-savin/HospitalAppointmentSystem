package com.example.hospitalappointmentsystem.patientmanagement.presentationlayer.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PatientRequestModel {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;
}
