package com.iodigital.tedtalks.entity;

import jakarta.persistence.*;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    public String name;

    public Author() { /* Empty constructor required by Hibernate */ }

    public Author(String name) {
        // Assignments / null-checks
        this.name = Objects.requireNonNull(name);
    }
}
