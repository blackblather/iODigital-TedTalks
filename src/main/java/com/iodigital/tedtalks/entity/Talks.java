package com.iodigital.tedtalks.entity;

import jakarta.persistence.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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

    @Column(name = "date")
    public LocalDate date;

    public Talks() { /* Empty constructor required by Hibernate */ }

    public Talks(Author author,
                 String title,
                 Integer views,
                 Integer likes,
                 String url,
                 String dateStr) {
        // Assignments / null-checks
        this.author = Objects.requireNonNull(author);
        this.title = Objects.requireNonNull(title);
        this.views = Objects.requireNonNull(views);
        this.likes = Objects.requireNonNull(likes);
        this.url = Objects.requireNonNull(url);
        this.influenceFactor = views * likes; // Calculate influence

        // Parse / assign date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
        YearMonth yearMonth = YearMonth.parse(dateStr, formatter);
        this.date = yearMonth.atDay(1);

        // Validations
        if (views < 0) {
            throw new IllegalArgumentException("View count must be positive");
        }
        if (likes < 0) {
            throw new IllegalArgumentException("Like count must be positive");
        }
    }
}
