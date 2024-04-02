package com.example.presentation;

import java.util.List;
import java.util.UUID;

import com.example.application.PostUsecase;
import com.example.domain.model.Post;
import com.example.infrastructure.inMemory.InMemoryPostRepository;
import com.example.infrastructure.panache.repository.PanachPostRepository;
import com.example.presentation.Schema.Post.CreatePostReponse;
import com.example.presentation.Schema.Post.GetAllPostResponse;
import com.example.presentation.Schema.Post.GetPostResponse;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
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
    final private PostUsecase postUsecase = new PostUsecase(new PanachPostRepository());

    @POST
    @Transactional
    public Response createPost(Post post) {
        try {
            Post createdPost = postUsecase.createPost(post);
            return Response.ok(new  CreatePostReponse(createdPost)).status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    public Response getPosts() {
        List<Post> posts = postUsecase.getPosts();
        return Response.ok(new GetAllPostResponse(posts)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePost(@PathParam("id") UUID id) {
        try {
            postUsecase.deletePost(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getPost(@PathParam("id") UUID id) {
        try {
            Post post = postUsecase.getPost(id);
            return Response.ok(new GetPostResponse(post)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
