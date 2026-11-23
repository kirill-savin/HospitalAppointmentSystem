package com.example.hospitalappointmentsystem.doctorsmanagement.presentationlayer.models;

import com.example.hospitalappointmentsystem.doctorsmanagement.domainlayer.Specialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorResponseModel {
    private UUID doctorId;
    private String firstName;
    private String lastName;
    private Specialization specialization;
}
