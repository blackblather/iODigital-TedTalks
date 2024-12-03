package com.iodigital.tedtalks.entity;

import jakarta.persistence.*;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "talks")
@SqlResultSetMapping(
        name="mostInfluentialTalksPerYearMapping",
        entities={
                @EntityResult(
                        entityClass= Talk.class,
                        fields= {
                                @FieldResult(name = "id", column = "t_id"),
                                @FieldResult(name = "title", column = "title"),
                                @FieldResult(name = "views", column = "views"),
                                @FieldResult(name = "likes", column = "likes"),
                                @FieldResult(name = "url", column = "url"),
                                @FieldResult(name = "influenceFactor", column = "influence_factor"),
                                @FieldResult(name = "year", column = "year"),
                        }
                ),
                @EntityResult(
                        entityClass= Author.class,
                        fields= {
                                @FieldResult(name = "id", column = "a_id"),
                                @FieldResult(name = "name", column = "name")
                        }
                )
        })
public class Talk {
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

    public Talk() { /* Empty constructor required by Hibernate */ }

    private Talk(Author author,
                String title,
                int views,
                int likes,
                String url,
                int year) {
        // Assignments / null-checks
        this.author = author;
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
        if (likes > views) {
            throw new IllegalArgumentException("Cannot have more likes than views");
        }
    }

    // Static factory methods for improved readability
    public static Talk withoutAuthor(String title, int views, int likes, String url, int year) {
        return new Talk(null, title, views, likes, url, year);
    }

    public static Talk withAuthor(Author author, String title, int views, int likes, String url, int year) {
        return new Talk(Objects.requireNonNull(author), title, views, likes, url, year);
    }

    public void calculateInfluenceFactor() {
        this.influenceFactor = (int) ((views * 0.5) + (likes * 0.5));
    }
}
