package com.example.hospitalappointmentsystem.appointmentmanagement.presentationlayer.models;

import com.example.hospitalappointmentsystem.appointmentmanagement.domainlayer.AppointmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseModel {
    private Integer id;
    private UUID doctorId;
    private UUID patientId;
    private LocalDate date;
    private String office;
    private AppointmentType type;
}