package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequestModel {

    @NotBlank
    private String name;

    @NotBlank
    private String street;

    @NotBlank
    private String province;

    @NotBlank
    private String city;

    @NotBlank
    private String postalCode;

    // Value Object relationship: send values.
    private List<String> phoneNumbers;
}
