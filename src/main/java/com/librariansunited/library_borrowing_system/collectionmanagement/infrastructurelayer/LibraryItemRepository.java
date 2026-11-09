package com.librariansunited.library_borrowing_system.collectionmanagement.infrastructurelayer;


import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.LibraryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LibraryItemRepository  extends JpaRepository <LibraryItem, Integer>{

    // Lookup by the PUBLIC identifier (the UUID business key).
    // Derived path: itemId (embedded) -> value -> column item_id
    Optional findByItemIdValue(UUID value);

    boolean existsByItemIdValue(UUID value);

    // Polymorphic subclass queries: each returns one kind only.
    @Query("select i from Book i")
    List findAllBooks();

    @Query("select i from AudioMaterial i")
    List findAllAudioMaterials();

    @Query("select i from VideoMaterial i")
    List findAllVideoMaterials();
}
