package com.example.getting.started.Post;

import java.util.List;
import java.util.UUID;

import com.example.getting.started.Post.Datastore.Inmemory;
import com.example.getting.started.Post.Entity.Post;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostService {

    public  List<Post> allPosts() {
      return Post.listAll();
    }

    public Post getById(UUID id) {
      return (Post) Post.findByIdOptional(id).map(post -> post).orElse(null);
    }

    public Post createPost(Post post) {
      post.persist();
      if (post.isPersistent()) {
        return post;
      }
      return null;
    }

    public Post updatePost(Post post) {
      for (int i = 0; i < Inmemory.posts.size(); i++) {
        if (Inmemory.posts.get(i).id.equals(post.id)) {
          Inmemory.posts.set(i, post);
          return post;
        }
      }
      return null;
    }

    public boolean deletePost(UUID id) {
      boolean deleted = Post.deleteById(id);
      if (deleted) return true;
      return false;
    }

}
