package com.iodigital.tedtalks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TalkListDto {
    @JsonProperty("talks")
    public List<TalkDto> talks;
}
