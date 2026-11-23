package com.example.hospitalappointmentsystem.appointmentmanagement.datamappinglayer;

import com.example.hospitalappointmentsystem.appointmentmanagement.domainlayer.Appointment;
import com.example.hospitalappointmentsystem.appointmentmanagement.domainlayer.Date;
import com.example.hospitalappointmentsystem.appointmentmanagement.domainlayer.Office;
import com.example.hospitalappointmentsystem.appointmentmanagement.presentationlayer.models.AppointmentRequestModel;
import com.example.hospitalappointmentsystem.appointmentmanagement.presentationlayer.models.AppointmentResponseModel;
import com.example.hospitalappointmentsystem.doctorsmanagement.datamappinglayer.DoctorMapper;
import com.example.hospitalappointmentsystem.appointmentmanagement.domainlayer.DoctorId;
import com.example.hospitalappointmentsystem.appointmentmanagement.domainlayer.PatientId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentMapper {

    private final DoctorMapper doctorMapper;

    public Appointment toEntity(AppointmentRequestModel model) {
        return new Appointment(
                null,
                new DoctorId(model.getDoctorId()),
                new PatientId(model.getPatientId()),
                new Date(model.getDate()),
                new Office(model.getOffice().trim()),
                model.getType()
        );
    }

    public void updateEntity(Appointment appointment, AppointmentRequestModel model) {
        appointment.setDoctor(new DoctorId(model.getDoctorId()));
        appointment.setPatient(new PatientId(model.getPatientId()));
        appointment.setDate(new Date(model.getDate()));
        appointment.setOffice(new Office(model.getOffice().trim()));
        appointment.setType(model.getType());
    }

    public AppointmentResponseModel toResponseModel(Appointment appointment) {
        return new AppointmentResponseModel(
                appointment.getId(),
                appointment.getDoctor() != null ? appointment.getDoctor().getDoctorId() : null,
                appointment.getPatient() != null ? appointment.getPatient().getPatientId() : null,
                appointment.getDate() != null ? appointment.getDate().getDate() : null,
                appointment.getOffice() != null ? appointment.getOffice().getOffice() : null,
                appointment.getType()
        );
    }

}
