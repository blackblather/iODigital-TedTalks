package com.iodigital.tedtalks.controller;

import com.iodigital.tedtalks.dto.RefreshResponseDTO;
import com.iodigital.tedtalks.service.ScraperService;
import com.iodigital.tedtalks.service.TedTalksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/talks")
public class TalksController {

    private ScraperService scraperService;
    private TedTalksService talksService;

    public TalksController(ScraperService scraperService,
                           TedTalksService talksService) {
        this.scraperService = scraperService;
        this.talksService = talksService;
    }

    @GetMapping("/refresh")
    public ResponseEntity<RefreshResponseDTO> refreshTalks() {
        int newTalksFound = scraperService.refresh();

        RefreshResponseDTO resp = new RefreshResponseDTO();
        resp.newTalksFound = newTalksFound;

        return ResponseEntity.ok(resp);
    }
}
