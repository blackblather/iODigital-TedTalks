package com.iodigital.tedtalks.controller;

import com.iodigital.tedtalks.service.ScraperService;
import com.iodigital.tedtalks.service.TedTalksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TalksController {

    private ScraperService scraperService;
    private TedTalksService talksService;

    public TalksController(ScraperService scraperService,
                           TedTalksService talksService) {
        this.scraperService = scraperService;
        this.talksService = talksService;
    }

    @GetMapping("/refresh")
    public ResponseEntity<String> refreshTalks() {
        scraperService.refresh();
        return ResponseEntity.ok().build();
    }
}
