package com.iodigital.tedtalks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorDTO {
    @JsonProperty("name")
    public String name;

    @JsonProperty("influence")
    public String influence;
}
