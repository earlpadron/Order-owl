package dev.earl.order_owl.post;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

import java.util.List;

public interface PostGeneratedService {

    @GetExchange("/posts")
    List<Post> findAll();

    @GetExchange("/posts/{id}")
    Post getPost(@PathVariable Integer id);

    @PostExchange("/posts")
    Post createPost(Post post);

    @PutExchange("/posts/{id}")
    Post updatePost(@PathVariable Integer id, Post post);

    @DeleteMapping("/posts/{id}")
    void deletePost(@PathVariable Integer id);
}
