package com.example.application;

import java.util.List;
import java.util.UUID;

import com.example.domain.model.Comment;
import com.example.domain.repository.CommentRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

public class CommentUsecase {
    final private CommentRepository commentRepository;
    
    @Inject
    public CommentUsecase(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment, UUID postId) throws RuntimeException {
        Comment created = commentRepository.createComment(comment, postId);
        if (created == null) {
            throw new RuntimeException("Comment not created");
        }
        return created;
    }

    public void deleteComment(UUID id) throws NotFoundException {
        Comment comment = commentRepository.getComment(id);
        if (comment == null) {
            throw new NotFoundException("Comment not found");
        }
        commentRepository.deleteComment(id);
    }

    public Comment getComment(UUID id) throws NotFoundException{
        Comment comment = commentRepository.getComment(id);
        if (comment == null) {
            throw new NotFoundException("Comment not found");
        }
        return comment;
    }

    public List<Comment> getComments() {
        return commentRepository.getComments();
    }

    public List<Comment> getCommentsByPostId(UUID postId) {
        return commentRepository.getCommentsByPostId(postId);
    }
}
