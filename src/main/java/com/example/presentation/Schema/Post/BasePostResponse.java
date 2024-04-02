package com.example.presentation.Schema.Post;

import java.util.UUID;

import com.example.domain.model.Post;

public class BasePostResponse {
    public UUID id;
    public String title;
    public String content;

    public BasePostResponse(Post post) {
        this.id = post.id;
        this.title = post.title;
        this.content = post.content;
    }
}
