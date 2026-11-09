package com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer;

import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.*;

import java.util.List;
import java.util.UUID;

public interface CollectionService {
    List getAllItems();

    List getItemsByType(String itemType);

    LibraryItem getItemById(UUID itemId) throws Throwable;

    LibraryItem updateItemStatus(UUID itemId, ItemStatus newStatus)
            throws ItemNotFoundException, Throwable;

    void deleteItem(UUID itemId) throws ItemNotFoundException, Throwable;

    Book addBook(Book book, List<UUID> authorIds) throws Throwable;

    AudioMaterial addAudioMaterial(AudioMaterial audioMaterial);

    VideoMaterial addVideoMaterial(VideoMaterial videoMaterial);

    Author addAuthor(Author author);

    List getAllAuthors();

    Author getAuthorById(UUID authorId) throws AuthorNotFoundException;

    List getAuthorsOfBook(UUID itemId)
            throws ItemNotFoundException, Throwable;

    Author linkAuthorToBook(UUID itemId, UUID authorId)
            throws ItemNotFoundException, AuthorNotFoundException, Throwable;

    void unlinkAuthorFromBook(UUID itemId, UUID authorId)
            throws ItemNotFoundException, AuthorNotFoundException, Throwable;

    void deleteAuthor(UUID authorId) throws AuthorNotFoundException;

}