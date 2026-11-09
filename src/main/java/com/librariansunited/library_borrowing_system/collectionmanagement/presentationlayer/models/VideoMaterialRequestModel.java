package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models;

import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.MaterialFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoMaterialRequestModel {

    @NotBlank
    private String title;

    @NotNull
    private Integer publicationYear;

    @NotNull
    private MaterialFormat format;

    @NotNull
    private Integer durationMinutes;

    private String avgRating;
}
