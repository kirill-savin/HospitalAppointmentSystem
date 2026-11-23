package com.example.hospitalappointmentsystem.doctorsmanagement.presentationlayer.models;

import com.example.hospitalappointmentsystem.doctorsmanagement.domainlayer.Specialization;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoctorRequestModel {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private Specialization specialization;
}
