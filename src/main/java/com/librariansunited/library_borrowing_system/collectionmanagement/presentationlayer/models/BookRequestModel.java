package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestModel {

    @NotBlank
    private String title;

    @NotNull
    private Integer publicationYear;

    @NotBlank
    private String isbn;

    // Entity relationship: send identities.
    private List<UUID> authorIds;
}