package com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "collection_items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class LibraryItem {

    // Internal surrogate key. Persistence-only: never exposed through
    // the API, never placed in a DTO. AccessLevel.NONE suppresses the
    // Lombok getter so it cannot leak by accident.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.NONE)
    private int id;

    // External, domain-based identifier; the one the API speaks.
    @Embedded
    @Column(name = "item_id")
    private ItemId itemId;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_status", nullable = false)
    private ItemStatus itemStatus;

    @Embedded
    @Column(name = "publication_year")
    private PublicationYear publicationYear;


    protected LibraryItem(ItemId itemId, String title,
                          PublicationYear publicationYear) {
        if (itemId == null) {
            throw new IllegalArgumentException("Item id is required.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required.");
        }
        if (publicationYear == null) {
            throw new IllegalArgumentException("Publication year is required.");
        }
        this.itemId = itemId;
        this.title = title;
        this.publicationYear = publicationYear;
        this.itemStatus = ItemStatus.AVAILABLE;   // system-managed state
    }

    public ItemStatus getStatus() { return itemStatus; }   // alias for service/mapper

    public void updateStatus(ItemStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }
        this.itemStatus = newStatus;
    }

    public abstract String getDescription();
}
