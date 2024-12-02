package com.iodigital.tedtalks.controller;

import com.iodigital.tedtalks.service.FileService;
import com.iodigital.tedtalks.service.TedTalksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TalksController {

    private FileService fileService;
    private TedTalksService talksService;

    public TalksController(FileService fileService,
                           TedTalksService talksService) {
        this.fileService = fileService;
        this.talksService = talksService;
    }

    @GetMapping("/refresh")
    public ResponseEntity<String> refreshTalks() {
//        fileService.loadFile();
        talksService.testSave();
        return ResponseEntity.ok().build();
    }
}
