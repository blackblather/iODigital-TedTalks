package com.iodigital.tedtalks.entity;

import jakarta.persistence.*;

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
}
