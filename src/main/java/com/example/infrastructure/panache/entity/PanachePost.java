package com.example.infrastructure.panache.entity;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.example.domain.model.Post;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class PanachePost extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Length(min = 1, max = 100)
    public String title;

    @Length(min = 1, max = 1000)
    public String content;

    public PanachePost(Post post) {
        this.id = post.id;
        this.title = post.title;
        this.content = post.content;
    }

    public PanachePost() {
    }

    public Post toPost() {
        Post post = new Post();
        post.id = this.id;
        post.title = this.title;
        post.content = this.content;
        return post;
    }
}