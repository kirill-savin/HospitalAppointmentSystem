package com.example.hospitalappointmentsystem.appointmentmanagement.presentationlayer.models;

import com.example.hospitalappointmentsystem.appointmentmanagement.domainlayer.AppointmentType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter
public class AppointmentRequestModel {
    @NotNull(message = "Doctor ID is required")
    private UUID doctorId;

    @NotNull(message = "Patient ID is required")
    private UUID patientId;

    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date cannot be in the past")
    private LocalDate date;

    @NotBlank(message = "Office is required")
    private String office;

    @NotNull(message = "Appointment type is required")
    private AppointmentType type;
}
