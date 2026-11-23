package com.example.hospitalappointmentsystem.doctorsmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LastName {
    @NotBlank(message = "Last name is required")
    @Column(name="last_name")
    private String name;
}