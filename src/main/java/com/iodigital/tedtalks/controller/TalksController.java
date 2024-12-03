package com.iodigital.tedtalks.controller;

import com.iodigital.tedtalks.dto.*;
import com.iodigital.tedtalks.dto.mapper.TalksMapper;
import com.iodigital.tedtalks.service.ScraperService;
import com.iodigital.tedtalks.service.TedTalksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                talksMapper.wrapperToGetTalkRespDTO(
                        talksService.getAll()
                )
        );
    }

    @GetMapping("/get")
    public ResponseEntity<TalkDto> getById(@RequestParam Integer id) {
        return ResponseEntity.ok(
                talksMapper.talkToDto(
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
                                                      @RequestBody TalkDto talksDto) {
        talksService.updateById(
                id,
                talksMapper.dtoToTalk(talksDto)
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mostInfluential")
    public ResponseEntity<TalkListDto> getMostInfluential(@RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(
                talksMapper.listToTalkListDto(
                        talksService.getMostInfluential(limit)
                )
        );
    }
}
