package com.example.infrastructure.inMemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.domain.model.Post;
import com.example.domain.repository.PostRepository;

import jakarta.inject.Singleton;

@Singleton
public class InMemoryPostRepository implements PostRepository {

    List<Post> posts = new ArrayList<Post>();

    @Override
    public Post createPost(Post post) {
        post.id = UUID.randomUUID();
        posts.add(post);
        return post;
    }

    @Override
    public void deletePost(UUID id) {
        boolean deleted = posts.removeIf(post -> post.id.equals(id));
        if (!deleted) throw new RuntimeException("Post not found");
    }

    @Override
    public Optional<Post> getPost(UUID id) {
        for (Post post : posts) {
            if (post.id.equals(id)) {
                return Optional.of(post);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Post> getPosts() {
        return posts;
    }
    
}
