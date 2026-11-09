package com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer;

import java.util.UUID;

public class ItemNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    private final UUID itemId;

    public ItemNotFoundException(UUID itemId) {
        super("Library item not found with id: " + itemId);
        this.itemId = itemId;
    }

    public UUID getItemId() {
        return itemId;
    }
}