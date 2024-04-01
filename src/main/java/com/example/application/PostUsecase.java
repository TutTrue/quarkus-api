package com.example.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.domain.model.Post;
import com.example.domain.repository.PostRepository;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.NotFoundException;

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

    public void deletePost(UUID id) throws NotFoundException{
        Post post = postRepository.getPost(id);
        if (post == null) {
            throw new NotFoundException("Post not found");
        }
        postRepository.deletePost(id);
    }

    public Post getPost(UUID id) throws NotFoundException{
        Post post = postRepository.getPost(id);
        if (post == null) {
            throw new NotFoundException("Post not found");
        }
        return post;
    }

    public List<Post> getPosts() {
        return postRepository.getPosts();
    }
}
