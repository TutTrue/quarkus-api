package com.example.getting.started.Post.Entity;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Post extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Length(min = 1, max = 100)
    public String title;

    @Length(min = 1, max = 1000)
    public String content;

}
