package com.example.presentation.Schema.Comment;

import java.util.UUID;

import com.example.domain.model.Comment;

public class GetCommentResponse {
    public UUID id;
    public String content;
    public UUID postId;

    public GetCommentResponse(Comment comment) {
        this.id = comment.id;
        this.content = comment.content;
        this.postId = comment.post.id;
    }
}
