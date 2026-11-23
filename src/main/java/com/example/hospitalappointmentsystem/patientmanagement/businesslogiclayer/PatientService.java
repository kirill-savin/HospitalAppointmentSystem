package com.example.hospitalappointmentsystem.patientmanagement.businesslogiclayer;

import com.example.hospitalappointmentsystem.exceptions.ResourceNotFoundException;
import com.example.hospitalappointmentsystem.patientmanagement.datamappinglayer.PatientMapper;
import com.example.hospitalappointmentsystem.patientmanagement.domainlayer.Patient;
import com.example.hospitalappointmentsystem.patientmanagement.domainlayer.PatientId;
import com.example.hospitalappointmentsystem.patientmanagement.infrastructurelayer.PatientRepository;
import com.example.hospitalappointmentsystem.patientmanagement.presentationlayer.models.PatientRequestModel;
import com.example.hospitalappointmentsystem.patientmanagement.presentationlayer.models.PatientResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public List<PatientResponseModel> findAll() {
        return patientRepository.findAll().stream().map(patientMapper::toResponseModel).toList();
    }

    public PatientResponseModel findById(Integer id) {
        return patientMapper.toResponseModel(getById(id));
    }

    @Transactional
    public PatientResponseModel create(PatientRequestModel model) {
        Patient patient = patientMapper.toEntity(model);
        if (patient.getPatientId() == null) {
            patient.setPatientId(new PatientId());
        }
        return patientMapper.toResponseModel(patientRepository.save(patient));
    }

    @Transactional
    public PatientResponseModel update(Integer id, PatientRequestModel model) {
        Patient patient = getById(id);
        patientMapper.updateEntity(patient, model);
        return patientMapper.toResponseModel(patientRepository.save(patient));
    }

    @Transactional
    public void delete(Integer id) {
        patientRepository.delete(getById(id));
    }

    public Patient getById(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient " + id + " not found"));
    }

    public Patient getByUuidOrThrow(UUID uuid){
        return patientRepository.findByPatientId_PatientId(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Patient " + uuid + " not found"));
    }

    public void verifyUuidExists(UUID uuid) {
        patientRepository.findByPatientId_PatientId(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Patient " + uuid + " not found"));
    }
}
