package fr.thesakyo.TPSpring.service;

import fr.thesakyo.TPSpring.model.Post;
import fr.thesakyo.TPSpring.repository.PostRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Data
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Optional<Post> getPost(final Long id) { return postRepository.findById(id); }
    public Iterable<Post> getPosts() { return postRepository.findAll(); }

    public void deletePost(final Long id) { postRepository.deleteById(id); }
    public Post savePost(Post post) {

        Post savedPost = postRepository.save(post);

        LocalDateTime currentDate = LocalDateTime.now();
        Date date = Date.from(currentDate.atZone(ZoneId.systemDefault()).toInstant());
        savedPost.setDate(date);

        return savedPost;
    }
}