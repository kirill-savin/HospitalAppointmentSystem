package com.example.hospitalappointmentsystem.appointmentmanagement.presentationlayer.controllers;

import com.example.hospitalappointmentsystem.appointmentmanagement.businesslogiclayer.AppointmentService;
import com.example.hospitalappointmentsystem.appointmentmanagement.presentationlayer.models.AppointmentRequestModel;
import com.example.hospitalappointmentsystem.appointmentmanagement.presentationlayer.models.AppointmentResponseModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentResponseModel>> getAll() {
        return ResponseEntity.ok(appointmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseModel> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(appointmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AppointmentResponseModel> create(@Valid @RequestBody AppointmentRequestModel model) {
        AppointmentResponseModel created = appointmentService.create(model);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseModel> update(@PathVariable Integer id, @Valid @RequestBody AppointmentRequestModel model) {
        return ResponseEntity.ok(appointmentService.update(id, model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
