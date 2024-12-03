package com.iodigital.tedtalks.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorDTO {
    @JsonProperty("name")
    public String name;

    @JsonProperty("influence")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String influence;
}
