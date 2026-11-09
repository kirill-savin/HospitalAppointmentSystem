package com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.controllers;

import com.librariansunited.library_borrowing_system.collectionmanagement.businesslogiclayer.CollectionService;
import com.librariansunited.library_borrowing_system.collectionmanagement.datamappinglayer.CollectionMapper;
import com.librariansunited.library_borrowing_system.collectionmanagement.domainlayer.VideoMaterial;
import com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models.ItemResponseModel;
import com.librariansunited.library_borrowing_system.collectionmanagement.presentationlayer.models.VideoMaterialRequestModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/video-materials")
public class VideoMaterialController {

    private final CollectionService collectionService;
    private final CollectionMapper collectionMapper;

    public VideoMaterialController(
            CollectionService collectionService,
            CollectionMapper collectionMapper) {
        this.collectionService = collectionService;
        this.collectionMapper = collectionMapper;
    }

    @PostMapping
    public ResponseEntity<ItemResponseModel> addVideoMaterial(
            @RequestBody VideoMaterialRequestModel requestModel) {
        try {
            VideoMaterial videoMaterial =
                    this.collectionMapper
                            .requestModelToVideoMaterial(requestModel);
            VideoMaterial savedMaterial =
                    this.collectionService.addVideoMaterial(videoMaterial);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.collectionMapper
                            .itemToResponseModel(savedMaterial));
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}