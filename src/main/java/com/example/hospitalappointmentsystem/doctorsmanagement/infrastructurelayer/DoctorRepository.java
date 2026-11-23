package com.example.hospitalappointmentsystem.doctorsmanagement.infrastructurelayer;

import com.example.hospitalappointmentsystem.doctorsmanagement.domainlayer.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Optional<Doctor> findByFirstName(String firstname);
    Optional<Doctor> findByLastName(String lastname);
    Optional<Doctor> findByDoctorId(UUID doctorid);
    Optional<Doctor> findByDoctorId_DoctorId(UUID doctorId);
}
