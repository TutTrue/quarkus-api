package com.example.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.domain.model.Post;
import com.example.domain.repository.PostRepository;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class PostUsecase {
    final private PostRepository postRepository;

    @Inject
    public PostUsecase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return postRepository.createPost(post);
    }

    public void deletePost(UUID id) {
        postRepository.deletePost(id);
    }

    public Optional<Post> getPost(UUID id) {
        return postRepository.getPost(id);
    }

    public List<Post> getPosts() {
        return postRepository.getPosts();
    }
}
