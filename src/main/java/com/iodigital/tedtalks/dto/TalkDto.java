package com.iodigital.tedtalks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TalkDto {
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

    @JsonProperty("date")
    public String date;
}
