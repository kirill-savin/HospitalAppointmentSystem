package com.example.hospitalappointmentsystem.doctorsmanagement.datamappinglayer;

import com.example.hospitalappointmentsystem.doctorsmanagement.domainlayer.Doctor;
import com.example.hospitalappointmentsystem.doctorsmanagement.domainlayer.FirstName;
import com.example.hospitalappointmentsystem.doctorsmanagement.domainlayer.LastName;
import com.example.hospitalappointmentsystem.doctorsmanagement.domainlayer.Specialization;
import com.example.hospitalappointmentsystem.doctorsmanagement.presentationlayer.models.DoctorRequestModel;
import com.example.hospitalappointmentsystem.doctorsmanagement.presentationlayer.models.DoctorResponseModel;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public Doctor toEntity(DoctorRequestModel model) {
        return new Doctor(
                new FirstName(model.getFirstName().trim()),
                new LastName(model.getLastName().trim()),
                model.getSpecialization()
        );
    }

    public void updateEntity(Doctor doctor, DoctorRequestModel model) {
        doctor.setFirstName(new FirstName(model.getFirstName().trim()));
        doctor.setLastName(new LastName(model.getLastName().trim()));
        doctor.setSpecialization(model.getSpecialization());
    }

    public DoctorResponseModel toResponseModel(Doctor doctor) {
        return new DoctorResponseModel(
                doctor.getDoctorId() != null ? doctor.getDoctorId().getDoctorId() : null,
                doctor.getFirstName() != null ? doctor.getFirstName().getName() : null,
                doctor.getLastName() != null ? doctor.getLastName().getName() : null,
                doctor.getSpecialization()
        );
    }
}
