package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.controllers;

import com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer.CollectionService;
import com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer.ItemNotFoundException;
import com.librariansunited.library_borrowing_system.collectionmanagement.datamappinglayer.CollectionMapper;
import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.ItemStatus;
import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.LibraryItem;
import com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models.ItemResponseModel;
import com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models.ItemStatusRequestModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items")
public class LibraryItemController {

    private final CollectionService collectionService;
    private final CollectionMapper collectionMapper;

    public LibraryItemController(
            CollectionService collectionService,
            CollectionMapper collectionMapper) {
        this.collectionService = collectionService;
        this.collectionMapper = collectionMapper;
    }

    // ---------------------------------------------------------------- queries

    @GetMapping
    public ResponseEntity<List<ItemResponseModel>> getAllItems() {
        List responseModels =
                this.collectionMapper.itemListToResponseModelList(
                        this.collectionService.getAllItems());
        return ResponseEntity.ok(responseModels);
    }

    @GetMapping(params = "itemType")
    public ResponseEntity<List<ItemResponseModel>> getItemsByItemType(
            @RequestParam String itemType) {
        try {
            List responseModels =
                    this.collectionMapper.itemListToResponseModelList(
                            this.collectionService.getItemsByType(itemType));
            return ResponseEntity.ok(responseModels);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponseModel> getItem(
            @PathVariable UUID itemId) {
        try {
            ItemResponseModel responseModel =
                    this.collectionMapper.itemToResponseModel(
                            this.collectionService.getItemById(itemId));
            return ResponseEntity.ok(responseModel);
        }
        catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    // --------------------------------------------------------------- commands

    @PutMapping("/{itemId}/status")
    public ResponseEntity<ItemResponseModel> updateItemStatus(
            @PathVariable UUID itemId,
            @RequestBody ItemStatusRequestModel requestModel) {

        System.out.println("REQUEST: " + requestModel);
        try {
            LibraryItem updated =
                    this.collectionService.updateItemStatus(
                            itemId, ItemStatus.valueOf(requestModel.getStatus().toString().toUpperCase()));
            return ResponseEntity.ok(
                    this.collectionMapper.itemToResponseModel(updated));
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

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable UUID itemId) {
        try {
            this.collectionService.deleteItem(itemId);
            return ResponseEntity.noContent().build();
        }
        catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
