package com.example.presentation.Schema.Comment;

import java.util.UUID;

import com.example.domain.model.Comment;

public class CreateCommentResponse {
    public UUID id;
    public String content;
    public UUID postId;

    public CreateCommentResponse(Comment comment) {
        this.id = comment.id;
        this.content = comment.content;
        this.postId = comment.post.id;
    }
}
