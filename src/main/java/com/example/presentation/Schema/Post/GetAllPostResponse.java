package com.example.presentation.Schema.Post;

import java.util.List;

import com.example.domain.model.Post;

public class GetAllPostResponse {
    public List<GetPostResponse> posts;

    public GetAllPostResponse(List<Post> posts) {
        this.posts = posts.stream().map(GetPostResponse::new).toList();
    }
}
