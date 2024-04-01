package com.example.getting.started.Post;


import java.net.URI;
import java.util.UUID;

import com.example.getting.started.Post.Entity.Post;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
public class PostResource {

    @Inject
    PostService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allPosts() {
        return Response.ok(service.allPosts()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@Valid @PathParam("id") UUID id) {
        Post post = service.getById(id);
        if (post == null) {
            return Response.status(404).build();
        }
        return Response.ok(post).build();
    }
    

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPost(Post post) {
        // return Response.ok(post).build();
        Post isCreated = service.createPost(post);
        if (isCreated == null) {
            return Response.status(400).build();
        }
        return Response.created(URI.create("/post/" + isCreated.id)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePost(@PathParam("id") UUID id) {
        boolean isDeleted = service.deletePost(id);
        if (!isDeleted) return Response.status(404).build();
        return Response.noContent().build();
    }


}
