package com.example.presentation;

import java.util.List;
import java.util.UUID;

import com.example.application.CommentUsecase;
import com.example.domain.model.Comment;
import com.example.infrastructure.panache.repository.PanachCommentRepository;
import com.example.presentation.Schema.Comment.CreateCommentRequest;
import com.example.presentation.Schema.Comment.CreateCommentResponse;
import com.example.presentation.Schema.Comment.GetAllCommentsResponse;
import com.example.presentation.Schema.Comment.GetCommentResponse;

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

@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentController {
    final private CommentUsecase commentUsecase = new CommentUsecase(new PanachCommentRepository());

    @POST
    @Transactional
    public Response createComment(CreateCommentRequest commentRequest) {
        try {
            Comment createComment = commentUsecase.createComment(commentRequest.comment, commentRequest.postId);
            return Response
                    .ok(new CreateCommentResponse(createComment))
                    .status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    public Response getComments() {
        List<Comment> comments = commentUsecase.getComments();
        return Response.ok(new GetAllCommentsResponse(comments)).build();
    }

    @GET
    @Path("/{id}")
    public Response getComment(@PathParam("id") UUID id) {
        try {
            Comment comment = commentUsecase.getComment(id);
            return Response.ok(new GetCommentResponse(comment)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteComment(@PathParam("id") UUID id) {
        try {
            commentUsecase.deleteComment(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/post/{id}")
    public Response getCommentsByPost(@PathParam("id") UUID id) {
        List<Comment> comments = commentUsecase.getCommentsByPostId(id);
        return Response.ok(new GetAllCommentsResponse(comments)).build();
    }

}
