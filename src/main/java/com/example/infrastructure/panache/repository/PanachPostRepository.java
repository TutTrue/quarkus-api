package com.example.infrastructure.panache.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.domain.model.Post;
import com.example.domain.repository.PostRepository;
import com.example.infrastructure.panache.entity.PanachePost;


public class PanachPostRepository implements PostRepository{

    @Override
    public Post createPost(Post post) {
        PanachePost panachePost = new PanachePost(post);
        panachePost.persist();
        if (!panachePost.isPersistent()) {
            return null;
        }
        return panachePost.toPost();
    }

    @Override
    public void deletePost(UUID id) {
        PanachePost.delete("id", id);
    }

    @Override
    public Post getPost(UUID id) {
        PanachePost panachePost = PanachePost.findById(id);
        return panachePost != null ? panachePost.toPost() : null;
    }

    @Override
    public List<Post> getPosts() {
        List<PanachePost> panacheposts = PanachePost.listAll();
        List<Post> posts = new ArrayList<>();
        for (PanachePost panachePost : panacheposts) {
            posts.add(panachePost.toPost());
        }
        return posts;
    }
    
}
