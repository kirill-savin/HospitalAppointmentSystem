package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseModel {

    private String authorId;
    private String name;
    private String street;       // flattened from Address (one-to-one)
    private String province;
    private String city;
    private String postalCode;
    private List<String> phoneNumbers;  // flattened values (one-to-many)
}
