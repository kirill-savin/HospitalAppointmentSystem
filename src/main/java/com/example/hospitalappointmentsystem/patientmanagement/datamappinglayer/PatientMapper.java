package com.example.hospitalappointmentsystem.patientmanagement.datamappinglayer;

import com.example.hospitalappointmentsystem.patientmanagement.domainlayer.FirstName;
import com.example.hospitalappointmentsystem.patientmanagement.domainlayer.LastName;
import com.example.hospitalappointmentsystem.patientmanagement.domainlayer.Patient;
import com.example.hospitalappointmentsystem.patientmanagement.domainlayer.PhoneNumber;
import com.example.hospitalappointmentsystem.patientmanagement.presentationlayer.models.PatientRequestModel;
import com.example.hospitalappointmentsystem.patientmanagement.presentationlayer.models.PatientResponseModel;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public Patient toEntity(PatientRequestModel model) {
        return new Patient(
                new FirstName(model.getFirstName().trim()),
                new LastName(model.getLastName().trim()),
                new PhoneNumber(model.getPhoneNumber().trim())
        );
    }

    public void updateEntity(Patient patient, PatientRequestModel model) {
        patient.setFirstName(new FirstName(model.getFirstName().trim()));
        patient.setLastName(new LastName(model.getLastName().trim()));
        patient.setPhoneNumber(new PhoneNumber(model.getPhoneNumber().trim()));
    }

    public PatientResponseModel toResponseModel(Patient patient) {
        return new PatientResponseModel(
                patient.getPatientId() != null ? patient.getPatientId().getPatientId() : null,
                patient.getFirstName() != null ? patient.getFirstName().getName() : null,
                patient.getLastName() != null ? patient.getLastName().getName() : null,
                patient.getPhoneNumber() != null ? patient.getPhoneNumber().getValue() : null
        );
    }
}
