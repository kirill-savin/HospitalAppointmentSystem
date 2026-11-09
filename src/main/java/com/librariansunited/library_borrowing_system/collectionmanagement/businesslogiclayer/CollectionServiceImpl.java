package com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer;

import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.*;
import com.librariansunited.library_borrowing_system.collectionmanagement.infrastructurelayer.AuthorRepository;
import com.librariansunited.library_borrowing_system.collectionmanagement.infrastructurelayer.BookRepository;
import com.librariansunited.library_borrowing_system.collectionmanagement.infrastructurelayer.LibraryItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
//@Transactional means that all public methods of this class are wrapped in a transaction. If any exception is thrown, the transaction is rolled back.
// This is important for maintaining data integrity when multiple database
@Transactional
public class CollectionServiceImpl implements CollectionService {

    private final LibraryItemRepository itemRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public CollectionServiceImpl(
            LibraryItemRepository itemRepository,
            BookRepository bookRepository,
            AuthorRepository authorRepository) {
        this.itemRepository = itemRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    // ------------------------------------------------------------ queries

    @Override
    public List<LibraryItem> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<LibraryItem> getItemsByType(String itemType) {
        if (itemType == null || itemType.isBlank()) {
            throw new IllegalArgumentException("Item type is required.");
        }
        return switch (itemType.trim().toUpperCase()) {
            case "BOOK"  -> itemRepository.findAllBooks();
            case "AUDIO" -> itemRepository.findAllAudioMaterials();
            case "VIDEO" -> itemRepository.findAllVideoMaterials();
            default -> throw new IllegalArgumentException(
                    "Unknown item type: " + itemType
                            + ". Use BOOK, AUDIO or VIDEO.");
        };
    }

    @Override
    public LibraryItem getItemById(UUID itemId)
            throws ItemNotFoundException, Throwable {
        // Look up by the PUBLIC identifier (the UUID business key),
        // never by the surrogate key.
        return (LibraryItem) itemRepository.findByItemIdValue(itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId));
    }

    // ----------------------------------------------------------- commands

    @Override
    public LibraryItem updateItemStatus(UUID itemId, ItemStatus newStatus)
            throws ItemNotFoundException, Throwable {
        LibraryItem item = getItemById(itemId);
        item.updateStatus(newStatus);   // intention-revealing domain method
        System.out.println("ITEM : " + item.getItemStatus());
        return (LibraryItem) itemRepository.save(item);
    }

    @Override
    public void deleteItem(UUID itemId) throws ItemNotFoundException, Throwable {
        LibraryItem item = getItemById(itemId);

        // Business rule: an item that is on loan belongs to a member.
        if (item.getStatus() == ItemStatus.ON_LOAN) {
            throw new IllegalStateException(
                    "Item " + itemId + " is on loan and cannot be "
                            + "removed from the collection.");
        }

        itemRepository.delete(item);
    }

    @Override
    public Book addBook(Book book, List<UUID> authorIds) throws Throwable {

        validateItem(book);

        // Identity and status need no patching here: the mapper minted
        // the ItemId at construction, and the root constructor assigned
        // ItemStatus.AVAILABLE. There is no setter to call.

        // Business rule: two books cannot share an ISBN.
        if (bookRepository.existsByIsbnValue(
                book.getISBN().getValue())) {
            throw new DuplicateIsbnException(book.getISBN().getValue());
        }

        // Resolve entity references by identity, then enforce the
        // aggregate's own invariant through addAuthor.
        if (authorIds != null) {
            for (UUID authorId : authorIds) {
                Author author = (Author) authorRepository
                        .findByAuthorIdValue(authorId)
                        .orElseThrow(() -> new AuthorNotFoundException(authorId));
                book.addAuthor(author);
            }
        }

        return (Book) itemRepository.save(book);
    }

    @Override
    public AudioMaterial addAudioMaterial(AudioMaterial audioMaterial) {
        validateItem(audioMaterial);
        return (AudioMaterial) itemRepository.save(audioMaterial);
    }

    @Override
    public VideoMaterial addVideoMaterial(VideoMaterial videoMaterial) {
        validateItem(videoMaterial);
        return (VideoMaterial) itemRepository.save(videoMaterial);
    }

    @Override
    public Author addAuthor(Author author) {
        validateAuthor(author);
        return (Author) authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(UUID authorId)
            throws AuthorNotFoundException{
        // Look up by the PUBLIC identifier (the UUID business key),
        // never by the surrogate key.
        try {
            return (Author) authorRepository.findByAuthorIdValue(authorId)
                    .orElseThrow(() -> new AuthorNotFoundException(authorId));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List getAuthorsOfBook(UUID itemId)
            throws ItemNotFoundException, Throwable {
        LibraryItem item = getItemById(itemId);
        if (!(item instanceof Book book)) {
            throw new IllegalArgumentException(
                    "Item " + itemId + " is not a book; "
                            + "only books have authors.");
        }
        return book.getAuthors();
    }

    @Override
    public Author linkAuthorToBook(UUID itemId, UUID authorId)
            throws ItemNotFoundException, AuthorNotFoundException, Throwable {
        LibraryItem item = getItemById(itemId);
        if (!(item instanceof Book book)) {
            throw new IllegalArgumentException(
                    "Authors can only be linked to books; item "
                            + itemId + " is not a book.");
        }
        Author author = getAuthorById(authorId);
        book.addAuthor(author);   // aggregate invariant: rejects duplicates
        itemRepository.save(book);
        return author;
    }

    @Override
    public void unlinkAuthorFromBook(UUID itemId, UUID authorId)
            throws ItemNotFoundException, AuthorNotFoundException, Throwable {
        LibraryItem item = getItemById(itemId);
        if (!(item instanceof Book book)) {
            throw new IllegalArgumentException(
                    "Authors can only be unlinked from books; item "
                            + itemId + " is not a book.");
        }
        Author linked = book.getAuthors().stream()
                .filter(a -> a.getAuthorId().getValue().equals(authorId))
                .findFirst()
                .orElseThrow(() -> new AuthorNotFoundException(
                        "Author " + authorId + " is not linked to item "+ itemId + "."));
        book.removeAuthor(linked);
        itemRepository.save(book);
    }

    @Override
    public void deleteAuthor(UUID authorId)
            throws AuthorNotFoundException {
        Author author = getAuthorById(authorId);
        authorRepository.delete(author);
    }

    // --------------------------------------------------------- validation

    private void validateItem(LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException(
                    "Item cannot be null.");
        }
        if (item.getTitle() == null || item.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required.");
        }
        if (item.getPublicationYear() == null) {
            throw new IllegalArgumentException(
                    "Publication year is required.");
        }
    }

    private void validateAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException(
                    "Author cannot be null.");
        }
        if (author.getName() == null || author.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (author.getAddress() == null) {
            throw new IllegalArgumentException("Address is required.");
        }
    }
}
