package com.iodigital.tedtalks.entity;

import jakarta.persistence.*;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "authors")
@SqlResultSetMapping(
        name = "mostInfluentialAuthorsMapping",
        classes = @ConstructorResult(
                targetClass = Author.class,
                columns = {
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "author_influence", type = Long.class)
                }
        )
)
public class Author {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    public String name;

    @Transient
    public Long influence;

    // Constructors
    public Author() { /* Empty constructor (required by Hibernate) */ }

    public Author(String name) {
        this(name, null);
    }

    public Author(String name, Long influence) {
        // Assignments / null-checks
        this.name = Objects.requireNonNull(name).trim();
        this.influence = influence;
    }
}
