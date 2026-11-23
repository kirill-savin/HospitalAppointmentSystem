package com.example.hospitalappointmentsystem.doctorsmanagement.businesslogiclayer;

import com.example.hospitalappointmentsystem.appointmentmanagement.datamappinglayer.AppointmentMapper;
import com.example.hospitalappointmentsystem.appointmentmanagement.infrastructurelayer.AppointmentRepository;
import com.example.hospitalappointmentsystem.doctorsmanagement.datamappinglayer.DoctorMapper;
import com.example.hospitalappointmentsystem.doctorsmanagement.domainlayer.Doctor;
import com.example.hospitalappointmentsystem.doctorsmanagement.domainlayer.DoctorId;
import com.example.hospitalappointmentsystem.doctorsmanagement.infrastructurelayer.DoctorRepository;
import com.example.hospitalappointmentsystem.doctorsmanagement.presentationlayer.models.DoctorRequestModel;
import com.example.hospitalappointmentsystem.doctorsmanagement.presentationlayer.models.DoctorResponseModel;
import com.example.hospitalappointmentsystem.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorMapper doctorMapper;
    private final AppointmentMapper appointmentMapper;

    public List<DoctorResponseModel> findAll() {
        return doctorRepository.findAll().stream().map(doctorMapper::toResponseModel).toList();
    }

    public DoctorResponseModel findById(Integer id) {
        return doctorMapper.toResponseModel(getByIdOrThrow(id));
    }

    @Transactional
    public DoctorResponseModel create(DoctorRequestModel model) {
        Doctor doctor = doctorMapper.toEntity(model);
        if (doctor.getDoctorId() == null) {
            doctor.setDoctorId(new DoctorId());
        }
        return doctorMapper.toResponseModel(doctorRepository.save(doctor));
    }

    @Transactional
    public DoctorResponseModel update(Integer id, DoctorRequestModel model) {
        Doctor doctor = getByIdOrThrow(id);
        doctorMapper.updateEntity(doctor, model);
        return doctorMapper.toResponseModel(doctorRepository.save(doctor));
    }

    @Transactional
    public void delete(Integer id) {
        doctorRepository.delete(getByIdOrThrow(id));
    }
    

    public Doctor getByIdOrThrow(Integer id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor " + id + " not found"));
    }

    public Doctor getByUuidOrThrow(UUID uuid) {
        return doctorRepository.findByDoctorId_DoctorId(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor " + uuid + " not found"));
    }
}
