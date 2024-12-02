package com.iodigital.tedtalks.controller;

import com.iodigital.tedtalks.dto.GetTalksResponseDto;
import com.iodigital.tedtalks.dto.RefreshResponseDTO;
import com.iodigital.tedtalks.dto.mapper.TalksMapper;
import com.iodigital.tedtalks.service.ScraperService;
import com.iodigital.tedtalks.service.TedTalksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/talks")
public class TalksController {
    // Mappers
    private TalksMapper talksMapper;

    // Service
    private ScraperService scraperService;
    private TedTalksService talksService;

    public TalksController(TalksMapper talksMapper,
                           ScraperService scraperService,
                           TedTalksService talksService) {
        this.talksMapper = talksMapper;
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

    @GetMapping("/getAll")
    public ResponseEntity<GetTalksResponseDto> getAll() {
        return ResponseEntity.ok(
                talksMapper.talkListWrapperToGetTalkRespDTO(
                        talksService.getAll()
                )
        );
    }
}
