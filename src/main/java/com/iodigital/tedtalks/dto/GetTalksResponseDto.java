package com.iodigital.tedtalks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetTalksResponseDto {
    @JsonProperty("total")
    public Long total;

    @JsonProperty("talks")
    public List<TalkDto> talks;
}
