package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorSummaryModel {
    private String authorId;
    private String name;
}
