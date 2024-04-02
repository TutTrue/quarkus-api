package com.example.presentation.Schema.Comment;

import java.util.ArrayList;
import java.util.List;

import com.example.domain.model.Comment;

public class GetAllCommentsResponse {
    public List<GetCommentResponse> comments;

    public GetAllCommentsResponse(List<Comment> comments) {
        this.comments = new ArrayList<>();
        for (Comment comment : comments) {
            this.comments.add(new GetCommentResponse(comment));
        }
    }

}
