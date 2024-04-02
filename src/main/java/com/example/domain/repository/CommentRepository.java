package com.example.domain.repository;

import java.util.List;
import java.util.UUID;

import com.example.domain.model.Comment;

public interface CommentRepository {

    Comment createComment(Comment comment, UUID postId);

    void deleteComment(UUID id);

    Comment getComment(UUID id);

    List<Comment> getComments();

    List<Comment> getCommentsByPostId(UUID postId);

}
