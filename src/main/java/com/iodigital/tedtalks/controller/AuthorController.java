package com.iodigital.tedtalks.controller;

import com.iodigital.tedtalks.dto.InfluentialAuthorsResponseDTO;
import com.iodigital.tedtalks.dto.mapper.DtoMapper;
import com.iodigital.tedtalks.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    // Services
    private final AuthorService authorService;

    // Mapper
    private final DtoMapper mapper;

    public AuthorController(AuthorService authorService,
                            DtoMapper mapper) {
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @GetMapping("/mostInfluential")
    public ResponseEntity<InfluentialAuthorsResponseDTO> getMostInfluential(@RequestParam(required = false, defaultValue = "10") Integer limit) {
        return ResponseEntity.ok(
                mapper.authorsToInfluentialAuthorsDto(
                        authorService.getMostInfluential(limit)
                )
        );
    }
}
