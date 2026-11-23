package com.example.hospitalappointmentsystem.appointmentmanagement.infrastructurelayer;

import com.example.hospitalappointmentsystem.appointmentmanagement.domainlayer.Appointment;
import com.example.hospitalappointmentsystem.appointmentmanagement.domainlayer.DoctorId;
import com.example.hospitalappointmentsystem.appointmentmanagement.domainlayer.PatientId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findByDoctor_doctorId(UUID doctorId);
    List<Appointment> findByPatient_patientId(UUID patientId);
    Optional<Appointment> findById(Integer id);
    List<Appointment> findAll();
}
