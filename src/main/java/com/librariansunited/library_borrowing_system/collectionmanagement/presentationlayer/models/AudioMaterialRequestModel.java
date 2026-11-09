package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AudioMaterialRequestModel {

    @NotBlank
    private String title;

    @NotNull
    private Integer publicationYear;

    private String narrator;

    @NotNull
    private Integer durationMinutes;
}
