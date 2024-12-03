package com.iodigital.tedtalks.controller;

import com.iodigital.tedtalks.dto.*;
import com.iodigital.tedtalks.dto.mapper.DtoMapper;
import com.iodigital.tedtalks.service.ScraperService;
import com.iodigital.tedtalks.service.TedTalksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/talks")
public class TalksController {
    // Mappers
    private DtoMapper mapper;

    // Services
    private ScraperService scraperService;
    private TedTalksService talksService;

    public TalksController(DtoMapper mapper,
                           ScraperService scraperService,
                           TedTalksService talksService) {
        this.mapper = mapper;
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
    public ResponseEntity<GetTalksResponseDTO> getAll() {
        return ResponseEntity.ok(
                mapper.wrapperToGetTalkRespDTO(
                        talksService.getAll()
                )
        );
    }

    @GetMapping("/get")
    public ResponseEntity<TalkDTO> getById(@RequestParam Integer id) {
        return ResponseEntity.ok(
                mapper.talkToDto(
                        talksService.getByIdOrThrow(id)
                )
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTalk(@RequestParam Integer id) {
        talksService.removeById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateTalkDetailsById(@RequestParam Integer id,
                                                      @RequestBody TalkDTO talksDto) {
        talksService.updateById(
                id,
                mapper.dtoToTalk(talksDto)
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mostInfluentialPerYear")
    public ResponseEntity<TalkListDTO> getMostInfluential() {
        return ResponseEntity.ok(
                mapper.listToTalkListDTO(
                        talksService.getMostInfluential()
                )
        );
    }
}
