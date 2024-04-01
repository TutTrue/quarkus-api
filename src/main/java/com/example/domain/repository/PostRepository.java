package com.example.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.domain.model.Post;

public interface PostRepository {

    Post createPost(Post post);

    void deletePost(UUID id);

    Optional<Post> getPost(UUID id);

    List<Post> getPosts();
}