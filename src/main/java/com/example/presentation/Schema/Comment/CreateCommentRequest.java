package com.example.presentation.Schema.Comment;

import java.util.UUID;

import com.example.domain.model.Comment;

public class CreateCommentRequest {
    public Comment comment;
    public UUID postId;
}