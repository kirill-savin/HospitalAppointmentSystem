package com.example.hospitalappointmentsystem.patientmanagement.presentationlayer.controllers;

import com.example.hospitalappointmentsystem.patientmanagement.businesslogiclayer.PatientService;
import com.example.hospitalappointmentsystem.patientmanagement.presentationlayer.models.PatientRequestModel;
import com.example.hospitalappointmentsystem.patientmanagement.presentationlayer.models.PatientResponseModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientResponseModel>> getAll() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseModel> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(patientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PatientResponseModel> create(@Valid @RequestBody PatientRequestModel model) {
        return ResponseEntity.ok(patientService.create(model));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseModel> update(@PathVariable Integer id, @Valid @RequestBody PatientRequestModel model) {
        return ResponseEntity.ok(patientService.update(id, model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
