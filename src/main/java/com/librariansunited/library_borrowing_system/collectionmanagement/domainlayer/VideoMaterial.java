package com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("VIDEO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VideoMaterial extends LibraryItem {

    @Embedded
    private Duration duration;

    @Enumerated(EnumType.STRING)
    private MaterialFormat format;

    private String avgRating;

    public VideoMaterial(ItemId itemId, String title,
                         PublicationYear publicationYear,
                         Duration duration, MaterialFormat format,
                         String avgRating) {
        super(itemId, title, publicationYear);
        if (duration == null) {
            throw new IllegalArgumentException("Duration is required.");
        }
        if (format == null) {
            throw new IllegalArgumentException("Format is required.");
        }
        if (avgRating != null && !avgRating.matches("\\d(\\.\\d)?")) {
            throw new IllegalArgumentException(
                    "Average rating must look like 4.7, got: " + avgRating);
        }
        this.duration = duration;
        this.format = format;
        this.avgRating = avgRating;
    }
    public VideoMaterial(String title,
                         PublicationYear publicationYear,
                         Duration duration, MaterialFormat format,
                         String avgRating) {
        this(ItemId.generate(), title, publicationYear, duration,  format,
                avgRating);
    }

    @Override
    public String getDescription() {
        return "Video material: " + getTitle()
                + " [" + format + ", rated " + avgRating + "]";
    }
}