package com.example.infrastructure.panache.entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.example.domain.model.Post;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "post")
    public List<PanacheComment> comments;

    public PanachePost(Post post) {
        this.id = post.id;
        this.title = post.title;
        this.content = post.content;
        this.comments = null;
        // post.comments.stream().map(comment -> new PanacheComment(comment)).toList();
    }

    public PanachePost() {
    }

    public Post toPost() {
        Post post = new Post();
        post.id = this.id;
        post.title = this.title;
        post.content = this.content;
        // post.comments = this.comments.stream().map(comment -> comment.toComment()).toList();
        return post;
    }
}