package com.iodigital.tedtalks.entity;

import jakarta.persistence.*;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "talks")
public class Talks {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=IDENTITY)
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

    @Column(name = "year")
    public Integer year;

    public Talks() { /* Empty constructor required by Hibernate */ }

    public Talks(Author author,
                 String title,
                 int views,
                 int likes,
                 String url,
                 int year) {
        // Assignments / null-checks
        this.author = Objects.requireNonNull(author);
        this.title = Objects.requireNonNull(title);
        this.views = views;
        this.likes = likes;
        this.url = Objects.requireNonNull(url);
        this.year = year;
        calculateInfluenceFactor();

        // Validations
        if (views < 0) {
            throw new IllegalArgumentException("View count must be positive");
        }
        if (likes < 0) {
            throw new IllegalArgumentException("Like count must be positive");
        }
        if (year < 0) {
            throw new IllegalArgumentException("Year must be positive");
        }
    }

    public void calculateInfluenceFactor() {
        this.influenceFactor = (int) ((views * 0.5) + (likes * 0.5));
    }
}
