package com.iodigital.tedtalks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InfluentialAuthorsResponseDTO {
    @JsonProperty("authors")
    public List<AuthorDTO> authorsDTO;
}
