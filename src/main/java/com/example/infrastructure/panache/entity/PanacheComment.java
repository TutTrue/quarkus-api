package com.example.infrastructure.panache.entity;

import java.util.UUID;

import com.example.domain.model.Comment;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment")
public class PanacheComment extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    public String content;

    @ManyToOne
    public PanachePost post;

    public PanacheComment() {
    }

    public PanacheComment(Comment comment) {
        this.id = comment.id;
        this.content = comment.content;
        this.post = null;
        // new PanachePost(comment.post);
    }

    public Comment toComment() {
        Comment comment = new Comment();
        comment.id = this.id;
        comment.content = this.content;
        comment.post = this.post.toPost();
        return comment;
    }
}
