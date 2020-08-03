package com.brunner.social.service;

import com.brunner.social.exception.ResourceNotFoundException;
import com.brunner.social.model.Post;
import com.brunner.social.model.User;
import com.brunner.social.repository.PostRepository;
import com.brunner.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    public Post InsertPost(Post post, Long UserId) {

        Optional<User> user = userRepository.findById(UserId);
        if (user != null) {
            post.setUser(user.get());
            return post;
        } else {
            throw new ResourceNotFoundException("User Not Found");
        }
    }

    public Post EditPost(Long postId, Post post) {
        return postRepository.findById(postId).map(Updatedpost -> {
            Updatedpost.setTitle(post.getTitle());
            Updatedpost.setDescription(post.getDescription());
            Updatedpost.setContent(post.getContent());
            return postRepository.save(Updatedpost);
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("PostId %s not found", postId)));
    }

    public ResponseEntity<?> deletePost(Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("PostId %s not found", postId)));
    }
}
