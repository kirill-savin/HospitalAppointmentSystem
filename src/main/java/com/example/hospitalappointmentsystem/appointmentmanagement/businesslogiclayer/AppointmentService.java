package com.example.hospitalappointmentsystem.appointmentmanagement.businesslogiclayer;

import com.example.hospitalappointmentsystem.appointmentmanagement.datamappinglayer.AppointmentMapper;
import com.example.hospitalappointmentsystem.appointmentmanagement.domainlayer.Appointment;
import com.example.hospitalappointmentsystem.appointmentmanagement.infrastructurelayer.AppointmentRepository;
import com.example.hospitalappointmentsystem.appointmentmanagement.presentationlayer.models.AppointmentRequestModel;
import com.example.hospitalappointmentsystem.appointmentmanagement.presentationlayer.models.AppointmentResponseModel;
import com.example.hospitalappointmentsystem.doctorsmanagement.businesslogiclayer.DoctorService;
import com.example.hospitalappointmentsystem.exceptions.ResourceNotFoundException;
import com.example.hospitalappointmentsystem.patientmanagement.businesslogiclayer.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentMapper appointmentMapper;

    public List<AppointmentResponseModel> findAll() {
        return appointmentRepository.findAll().stream().map(appointmentMapper::toResponseModel).toList();
    }

    public AppointmentResponseModel findById(Integer id) {
        return appointmentMapper.toResponseModel(getById(id));
    }

    @Transactional
    public AppointmentResponseModel create(AppointmentRequestModel model) {
        doctorService.getByUuidOrThrow(model.getDoctorId());
        patientService.getByUuidOrThrow(model.getPatientId());
        return appointmentMapper.toResponseModel(appointmentRepository.save(appointmentMapper.toEntity(model)));
    }

    @Transactional
    public AppointmentResponseModel update(Integer id, AppointmentRequestModel model) {
        Appointment appointment = getById(id);
        doctorService.getByUuidOrThrow(model.getDoctorId());
        patientService.getByUuidOrThrow(model.getPatientId());
        appointmentMapper.updateEntity(appointment, model);
        return appointmentMapper.toResponseModel(appointmentRepository.save(appointment));
    }

    @Transactional
    public void delete(Integer id) {
        appointmentRepository.delete(getById(id));
    }


    private Appointment getById(Integer id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment " + id + " not found"));
    }
}
