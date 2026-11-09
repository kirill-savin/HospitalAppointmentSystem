package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.controllers;

import com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer.CollectionService;
import com.librariansunited.library_borrowing_system.collectionmanagement.datamappinglayer.CollectionMapper;
import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.AudioMaterial;
import com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models.AudioMaterialRequestModel;
import com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models.ItemResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/audio-materials")
public class AudioMaterialController {

    private final CollectionService collectionService;
    private final CollectionMapper collectionMapper;

    public AudioMaterialController(
            CollectionService collectionService,
            CollectionMapper collectionMapper) {
        this.collectionService = collectionService;
        this.collectionMapper = collectionMapper;
    }

    @PostMapping
    public ResponseEntity<ItemResponseModel> addAudioMaterial(
            @RequestBody AudioMaterialRequestModel requestModel) {
        try {
            AudioMaterial audioMaterial =
                    this.collectionMapper
                            .requestModelToAudioMaterial(requestModel);
            AudioMaterial savedMaterial =
                    this.collectionService.addAudioMaterial(audioMaterial);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.collectionMapper
                            .itemToResponseModel(savedMaterial));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}