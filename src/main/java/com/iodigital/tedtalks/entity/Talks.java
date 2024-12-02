package com.iodigital.tedtalks.entity;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "talks")
public class Talks {
    @Id
    @Column(name = "id")
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    public Author author;

    @Column(name = "title")
    public String title;

    @Column(name = "views")
    public Integer views;

    @Column(name = "likes")
    public Integer likes;

    @Column(name = "url")
    public String url;

    @Column(name = "influence_factor")
    public Integer influenceFactor;

    @Column(name = "date")
    public ZonedDateTime date;
}
