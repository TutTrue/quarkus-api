package com.example.infrastructure.panache.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.domain.model.Comment;
import com.example.domain.model.Post;
import com.example.domain.repository.CommentRepository;
import com.example.infrastructure.panache.entity.PanacheComment;
import com.example.infrastructure.panache.entity.PanachePost;

public class PanachCommentRepository implements CommentRepository {

    @Override
    public Comment createComment(Comment comment, UUID postId) {
        PanachePost post = PanachePost.findById(postId);
        if (post == null) {
            return null;
        }
        PanacheComment panachePost = new PanacheComment(comment);
        panachePost.post = post;
        panachePost.persist();
        if (!panachePost.isPersistent()) {
            return null;
        }
        return panachePost.toComment();
    }

    @Override
    public void deleteComment(UUID id) {
        PanacheComment.delete("id", id);
    }

    @Override
    public Comment getComment(UUID id) {
        PanacheComment panacheComment = PanacheComment.findById(id);
        return panacheComment != null ? panacheComment.toComment() : null;
    }

    @Override
    public List<Comment> getComments() {
        List<PanacheComment> panacheComments = PanacheComment.listAll();
        List<Comment> comments = new ArrayList<>();
        for (PanacheComment panachecomment : panacheComments) {
            comments.add(panachecomment.toComment());
        }
        return comments;
    }

    @Override
    public List<Comment> getCommentsByPostId(UUID postId) {
        PanachePost post = PanachePost.findById(postId);
        if (post == null) {
            return null;
        }
        List<PanacheComment> panacheComments = PanacheComment.list("post", post);
        List<Comment> comments = new ArrayList<>();
        for (PanacheComment panachecomment : panacheComments) {
            comments.add(panachecomment.toComment());
        }
        return comments;
    }
    
}
