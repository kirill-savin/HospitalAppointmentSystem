package com.example.hospitalappointmentsystem.patientmanagement.infrastructurelayer;

import com.example.hospitalappointmentsystem.patientmanagement.domainlayer.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository <Patient, Integer> {
    Optional<Patient> findByFirstName(String firstname);
    Optional<Patient> findByLastName(String lastname);
    Optional<Patient> findByPatientId(UUID patientId);
    Optional<Patient> findByPhoneNumber(String patientnumber);
    Optional<Patient> findByPatientId_PatientId(UUID patientId);
}
