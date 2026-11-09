package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models;

import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.ItemStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemStatusRequestModel {

    @NotNull
    private ItemStatus status;
}
