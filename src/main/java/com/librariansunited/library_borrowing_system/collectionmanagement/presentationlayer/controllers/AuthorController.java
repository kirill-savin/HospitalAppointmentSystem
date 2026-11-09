package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.controllers;

import com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer.AuthorNotFoundException;
import com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer.CollectionService;
import com.librariansunited.library_borrowing_system.collectionmanagement.datamappinglayer.CollectionMapper;
import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.Author;
import com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models.AuthorRequestModel;
import com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models.AuthorResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final CollectionService collectionService;
    private final CollectionMapper collectionMapper;

    public AuthorController(CollectionService collectionService,
                            CollectionMapper collectionMapper) {
        this.collectionService = collectionService;
        this.collectionMapper = collectionMapper;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseModel> addAuthor(
            @RequestBody AuthorRequestModel requestModel) {
        try {
            Author author =
                    this.collectionMapper.requestModelToAuthor(requestModel);
            Author savedAuthor =
                    this.collectionService.addAuthor(author);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.collectionMapper
                            .authorToResponseModel(savedAuthor));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List> getAllAuthors() {
        List responseModels =
                this.collectionMapper.authorListToResponseModelList(
                        this.collectionService.getAllAuthors());
        return ResponseEntity.ok(responseModels);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorResponseModel> getAuthor(
            @PathVariable UUID authorId) {
        try {
            AuthorResponseModel responseModel =
                    this.collectionMapper.authorToResponseModel(
                            this.collectionService.getAuthorById(authorId));
            return ResponseEntity.ok(responseModel);
        }
        catch (AuthorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deleteAuthor(
            @PathVariable UUID authorId) {
        try {
            this.collectionService.deleteAuthor(authorId);
            return ResponseEntity.noContent().build();
        }
        catch (AuthorNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}