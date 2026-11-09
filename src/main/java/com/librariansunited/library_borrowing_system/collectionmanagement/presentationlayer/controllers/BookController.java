package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.controllers;

import com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer.AuthorNotFoundException;
import com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer.CollectionService;
import com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer.DuplicateIsbnException;
import com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer.ItemNotFoundException;
import com.librariansunited.library_borrowing_system.collectionmanagement.datamappinglayer.CollectionMapper;
import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.Author;
import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.Book;
import com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models.AuthorSummaryModel;
import com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models.BookRequestModel;
import com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models.ItemResponseModel;
import com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models.AddAuthorToBookRequestModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final CollectionService collectionService;
    private final CollectionMapper collectionMapper;

    public BookController(CollectionService collectionService,
                          CollectionMapper collectionMapper) {
        this.collectionService = collectionService;
        this.collectionMapper = collectionMapper;
    }

    @PostMapping()
    public ResponseEntity<ItemResponseModel> addBook(
            @RequestBody BookRequestModel requestModel) {
        System.out.println("requestModel: " + requestModel);

        try {
            Book book =
                    this.collectionMapper.requestModelToBook(requestModel);
            Book savedBook = this.collectionService.addBook(
                    book, requestModel.getAuthorIds());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.collectionMapper
                            .itemToResponseModel(savedBook));
        }
        catch (DuplicateIsbnException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (AuthorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{bookId}/authors")
    public ResponseEntity<List> getAuthorsOfBook(
            @PathVariable UUID bookId) {
        try {
            List summaries =
                    this.collectionMapper.authorListToSummaryModelList(
                            this.collectionService
                                    .getAuthorsOfBook(bookId));
            return ResponseEntity.ok(summaries);
        }
        catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{bookId}/authors")
    public ResponseEntity<AuthorSummaryModel> addAuthorToBook(
            @PathVariable UUID bookId,
            @RequestBody AddAuthorToBookRequestModel requestModel) {
        try {
            Author author = this.collectionService.linkAuthorToBook(
                    bookId, requestModel.getAuthorId());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.collectionMapper
                            .authorToSummaryModel(author));
        }
        catch (ItemNotFoundException | AuthorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{bookId}/authors/{authorId}")
    public ResponseEntity<Void> removeAuthorFromBook(
            @PathVariable UUID bookId,
            @PathVariable UUID authorId) {
        try {
            this.collectionService.unlinkAuthorFromBook(bookId, authorId);
            return ResponseEntity.noContent().build();
        }
        catch (ItemNotFoundException | AuthorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}