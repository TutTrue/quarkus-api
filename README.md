# Getting started with Quarkus

This is a minimal CRUD service exposing a couple of endpoints over REST.

I tried to follow the clean architecture principles

Under the hood, this demo uses:

- RESTEasy to expose the REST endpoints
- REST-assured and JUnit 5 for endpoint testing

## Building the application

Launch the Maven build on the checked out sources of this demo:

> ./mvnw package

### Live coding with Quarkus

The Maven Quarkus plugin provides a development mode that supports
live coding. To try this out:

> ./mvnw quarkus:dev

This command will leave Quarkus running in the foreground listening on port 8080.

## API Reference

#### Get all posts

```http
  GET /posts
```
- Request:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |

- Response
```json
{
  "posts": [
    {
      "id": "1c4a3540-d637-4725-94a5-32c1b82cb444",
      "title": "This is post 1",
      "content": "Lorem ipsum dolor",
      "createdAt": "2024-04-02T16:09:49.797782",
      "updatedAt": "2024-04-02T16:09:49.797819"
    },
    {
      "id": "638b1405-97dd-45a1-866a-c5d2f2749d04",
      "title": "This is post 2",
      "content": "Lorem ipsum dolor sit ",
      "createdAt": "2024-04-02T16:09:49.805614",
      "updatedAt": "2024-04-02T16:09:49.80566"
    },
  ]
}
```
#### Get post

```http
  GET /posts/${id}
```
- Request

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
- Response
```json
{
  "id": "1c4a3540-d637-4725-94a5-32c1b82cb444",
  "title": "This is post 1",
  "content": "Lorem ipsum dolor",
  "createdAt": "2024-04-02T16:09:49.797782",
  "updatedAt": "2024-04-02T16:09:49.797819"
}
```


#### Delete post

```http
  DELETE /posts/${id}
```
- Request

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
- Response
```json
status: 204 no content
```

#### Create post

```http
  POST /posts/
```
- Request

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| title | String | **Required** |
| content | String | **Required** |
- Response
```json
{
  "id": "1c4a3540-d637-4725-94a5-32c1b82cb444",
  "title": "This is post 1",
  "content": "Lorem ipsum dolor",
}
```

#### Get all comments

```http
  GET /comments/
```
- Request:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
- Response
```json
{
  "comments": [
    {
      "id": "b95ac69e-c0ba-419b-87b8-56b2b00655ab",
      "content": "Nice post this is cool",
      "postId": "1c4a3540-d637-4725-94a5-32c1b82cb444",
      "createdAt": "2024-04-02T16:10:43.212616",
      "updatedAt": "2024-04-02T16:10:43.212648"
    },
    {
      "id": "0b98499e-4cb6-43b7-a718-7e5897c4afa6",
      "content": "good to hear that",
      "postId": "1c4a3540-d637-4725-94a5-32c1b82cb444",
      "createdAt": "2024-04-02T16:13:21.435209",
      "updatedAt": "2024-04-02T16:13:21.435233"
    },
  ]
}
```
#### Get comment by id

```http
  GET /comments/${id}/
```
- Request

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
- Response
```json
{
  "id": "0b98499e-4cb6-43b7-a718-7e5897c4afa6",
  "content": "hi this is",
  "postId": "1c4a3540-d637-4725-94a5-32c1b82cb444",
  "createdAt": "2024-04-02T16:13:21.435209",
  "updatedAt": "2024-04-02T16:13:21.435233"
}
```

#### Get comment by post id

```http
  GET /comments/posts/${postId}/
```
- Request

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
- Response
```json
{
  "id": "0b98499e-4cb6-43b7-a718-7e5897c4afa6",
  "content": "hi this is",
  "postId": "1c4a3540-d637-4725-94a5-32c1b82cb444",
  "createdAt": "2024-04-02T16:13:21.435209",
  "updatedAt": "2024-04-02T16:13:21.435233"
}
```


#### Delete comment

```http
  DELETE /comments/${id}
```
- Request

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
- Response
```json
status: 204 no content
```

#### Create comment

```http
  POST /comments/
```
- Request

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| postId | UUID | **Required** |
| comment | json | **Required**: comment:{"content": "My comment"} |
- Response
```json
{
  "id": "b569eeb6-2df2-432f-959e-8cded5acb9fc",
  "content": "My comment",
  "postId": "1c4a3540-d637-4725-94a5-32c1b82cb444",
}
```
