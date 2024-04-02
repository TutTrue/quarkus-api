package com.example.domain.model;

import java.util.List;
import java.util.UUID;

public class Post {
    public UUID id;
    public String title;
    public String content;
    public List<Comment> comments;
}
