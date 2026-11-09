package com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("AUDIO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AudioMaterial extends LibraryItem {

    @Embedded
    private Duration duration;

    @Column(name = "narrator")
    private String narrator;

    public AudioMaterial(ItemId itemId, String title,
                         PublicationYear publicationYear,
                         Duration duration, String narrator) {
        super(itemId, title, publicationYear);
        if (duration == null) {
            throw new IllegalArgumentException("Duration is required.");
        }
        if (narrator == null || narrator.isBlank()) {
            throw new IllegalArgumentException("Narrator is required.");
        }
        this.duration = duration;
        this.narrator = narrator;
    }
    public AudioMaterial(String title,
                         PublicationYear publicationYear,
                         Duration duration, String narrator) {
        this(ItemId.generate(), title, publicationYear, duration , narrator);

    }

    @Override
    public String getDescription() {
        return "Audio material: " + getTitle()
                + ", narrated by " + narrator
                + " (" + (duration == null ? "?" : duration.getMinutes())
                + " min)";
    }
}
