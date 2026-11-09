package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseModel {

    private String type;            // BOOK, AUDIO or VIDEO
    private String itemId;
    private String title;
    private String description;
    private Integer publicationYear;
    private String status;
    private String isbn;            // BOOK only
    private String narrator;        // AUDIO only
    private Integer durationMinutes;// AUDIO and VIDEO only
    private String format;          // VIDEO only
    private String avgRating;       // VIDEO only
    private List<AuthorSummaryModel> authors;  // BOOK only
}
