package com.example.hospitalappointmentsystem.appointmentmanagement.domainlayer;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private DoctorId doctor;

    @Embedded
    private PatientId patient;

    @Embedded
    private Date date;

    @Embedded
    private Office office;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_type", nullable = false)
    private AppointmentType type;
}
