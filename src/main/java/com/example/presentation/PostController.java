package com.example.presentation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.application.PostUsecase;
import com.example.domain.model.Post;
import com.example.infrastructure.inMemory.InMemoryPostRepository;
import com.example.infrastructure.panache.repository.PanachPostRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostController {
    final private PostUsecase postUsecase =new PostUsecase(new InMemoryPostRepository());

    @POST
    public Response createPost(Post post) {
        postUsecase.createPost(post);
        return Response.ok(post).build();
    }

    @GET
    public Response getPosts() {
        List<Post> posts = postUsecase.getPosts();
        return Response.ok(posts).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePost(@PathParam("id") UUID id) {
        try {
            postUsecase.deletePost(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getPost(@PathParam("id") UUID id) {
        Optional<Post> post = postUsecase.getPost(id);
        return Response.ok(post).build();
    }
}
