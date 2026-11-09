package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAuthorToBookRequestModel {

    @NotNull
    private UUID authorId;
}
