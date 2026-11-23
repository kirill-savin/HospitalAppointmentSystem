package com.example.hospitalappointmentsystem.doctorsmanagement.presentationlayer.controllers;

import com.example.hospitalappointmentsystem.doctorsmanagement.businesslogiclayer.DoctorService;
import com.example.hospitalappointmentsystem.doctorsmanagement.presentationlayer.models.DoctorRequestModel;
import com.example.hospitalappointmentsystem.doctorsmanagement.presentationlayer.models.DoctorResponseModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorResponseModel>> getAll() {
        return ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseModel> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(doctorService.findById(id));
    }

    

    @PostMapping
    public ResponseEntity<DoctorResponseModel> create(@Valid @RequestBody DoctorRequestModel model) {
        return ResponseEntity.ok(doctorService.create(model));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseModel> update(@PathVariable Integer id, @Valid @RequestBody DoctorRequestModel model) {
        return ResponseEntity.ok(doctorService.update(id, model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
