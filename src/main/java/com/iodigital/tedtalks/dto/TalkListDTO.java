package com.iodigital.tedtalks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TalkListDTO {
    @JsonProperty("talks")
    public List<TalkDTO> talks;
}
