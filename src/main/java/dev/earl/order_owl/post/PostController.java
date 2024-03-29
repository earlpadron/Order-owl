package dev.earl.order_owl.post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    List<Post> findAll(){
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post findPost(@PathVariable("id") Integer id){
        return postService.findPost(id);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post newPost){
        Post post = postService.createPost(newPost);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Integer id, @RequestBody Post updatedPost){
        Post post = postService.updatePost(id, updatedPost);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("id") Integer id){
        postService.delete(id);
    }


}
