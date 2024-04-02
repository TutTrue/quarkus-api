package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.domain.model.Post;
import com.example.infrastructure.panache.entity.PanachePost;
import com.example.presentation.PostController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(PostController.class)
class PostResourceTest {


    static List<PanachePost> Posts;

    @BeforeAll
    @Transactional
    static void setup() {
        // create 3 test Posts
        Posts = new ArrayList<>();
        createTestData();
    }

    @Transactional
    static void createTestData() {
        Post testPost1 = new Post();
        testPost1.title = "Headphones";
        testPost1.content = "Lorem ipsum dolor";


        Post testPost2 = new Post();
        testPost2.title = "Keyboard";
        testPost2.content = "Lorem ipsum dolor ";
        

        Post testPost3 = new Post();
        testPost3.title = "Mouse";
        testPost3.content = "Lorem ipsum dolor sit ";

        PanachePost post1 = new PanachePost(testPost1);
        PanachePost post2 = new PanachePost(testPost2);
        PanachePost post3 = new PanachePost(testPost3); 

        post1.persist();
        post2.persist();
        post3.persist();

        Posts.add(post1);
        Posts.add(post2);
        Posts.add(post3);
    }


    @Test
    void testGetAllEndpoint() {
        given()
                .when()
                .get("/")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void testGetPostByIdEndpoint() {
        // test a valid id
        given()
                .pathParam("id", Posts.get(0).id)
                .when()
                .get("/{id}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void testGetPostByBadId() {
        given()
                .pathParam("id", UUID.randomUUID())
                .when()
                .get("/{id}")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    void testCreatePostEndpoint() {
        String requestBody = "{\"title\":\"testTitle\",\"content\":\"Lorem ipsum dolor sit amet\"}";

        // store the number of records before and after creation
        long PostsCount = PanachePost.count();
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .body("title", is("testTitle"))
                .body("content", is("Lorem ipsum dolor sit amet"));
        long newPostsCount = PanachePost.count();


        Assertions.assertSame(PostsCount + 1, newPostsCount);
    }

}