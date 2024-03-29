package dev.earl.order_owl.post;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class PostService {

    private final RestClient restClient;

    /**
     * This is a client class for practice that calls rest api's from jsonplaceholder.typicode.com
     */
    public PostService() {
        restClient = RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    public List<Post> findAll(){
        return restClient.get()
                .uri("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Post>>(){
                });

    }

    public Post findPost(Integer id){
        return restClient.get()
                .uri("/posts/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Post.class);
    }

    public Post createPost(Post newPost) {
        return restClient.post()
                .uri("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .body(newPost)
                .retrieve()
                .body(Post.class);

    }

    public Post updatePost(Integer id, Post post){
        return restClient.put()
                .uri("/posts/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(post)
                .retrieve()
                .body(Post.class);
    }

    public void delete(Integer id){
        restClient.delete()
                .uri("posts/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
