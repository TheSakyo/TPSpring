package fr.thesakyo.TPSpring.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.thesakyo.TPSpring.model.Post;
import fr.thesakyo.TPSpring.model.User;
import fr.thesakyo.TPSpring.service.PostService;

@RestController
public class PostController {

	@Autowired
    private PostService postService;

    @GetMapping("/posts")
    public Iterable<Post> getPosts() { return postService.getPosts(); }
	@GetMapping("/post/{id}")
	public Post getPost(@PathVariable("id") final Long id) {

		Optional<Post> post = postService.getPost(id);
        return post.orElse(null);
	}

	@PostMapping("/post")
	public Post createPost(@RequestBody Post Post) { return postService.savePost(Post); }
	@PutMapping("/post/{id}")
	public Post updatePost(@PathVariable("id") final Long id, @RequestBody Post post) {

		Optional<Post> targetPost = postService.getPost(id);

		if(targetPost.isPresent()) {

			Post currentPost = targetPost.get();

			User user = post.getUser();
			if(user != null) { currentPost.setUser(user); }

			String title = post.getTitle();
			if(title != null) {	currentPost.setTitle(title); }

			String content = post.getContent();
			if(content != null) { currentPost.setContent(content); }

			String imageUrl = post.getImageUrl();
			if(imageUrl != null) { currentPost.setImageUrl(imageUrl); }

			Date date = post.getDate();
			if(date != null) { currentPost.setDate(date); }

			postService.savePost(currentPost);
			return currentPost;

		} else { return null; }
	}
	@DeleteMapping("/post/{id}")
	public void deletePost(@PathVariable("id") final Long id) { postService.deletePost(id); }
}