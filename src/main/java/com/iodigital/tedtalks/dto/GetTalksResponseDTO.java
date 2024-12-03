package com.iodigital.tedtalks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetTalksResponseDTO {
    @JsonProperty("total")
    public Long total;

    @JsonProperty("talks")
    public List<TalkDTO> talks;
}
