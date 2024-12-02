package com.iodigital.tedtalks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefreshResponseDTO {
    @JsonProperty("newTalksFound")
    public int newTalksFound;
}
