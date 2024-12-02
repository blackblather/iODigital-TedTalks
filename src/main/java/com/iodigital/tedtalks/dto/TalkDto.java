package com.iodigital.tedtalks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TalkDto {
    @JsonProperty("author")
    public AuthorDto author;

    @JsonProperty("title")
    public String title;

    @JsonProperty("views")
    public Integer views;

    @JsonProperty("likes")
    public Integer likes;

    @JsonProperty("url")
    public String url;

    @JsonProperty("influence_factor")
    public Integer influenceFactor;

    @JsonProperty("year")
    public Integer year;
}
