package com.iodigital.tedtalks.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    public String name;

    public Author() { /* Empty constructor required by Hibernate */ }

    public Author(String name) {
        // Validations
        if (name == null) {
            throw new NullPointerException("Author name must not be null");
        }

        // Assignments
        this.id = name.hashCode();  // Generate ID from name
        this.name = name;
    }
}
