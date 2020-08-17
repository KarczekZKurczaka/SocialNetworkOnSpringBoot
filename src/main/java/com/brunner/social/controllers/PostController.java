package com.brunner.social.controllers;

import com.brunner.social.RestResponse;
import com.brunner.social.exception.ResourceNotFoundException;
import com.brunner.social.model.Post;
import com.brunner.social.repository.PostRepository;
import com.brunner.social.repository.UserRepository;
import com.brunner.social.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    @ApiOperation(value = "Get all posts.")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @ApiOperation(value = "Create a posts for given user.")
    @PostMapping("/posts/{userid}")
    public RestResponse createPost(@Valid @RequestBody Post post, @PathVariable Long userid) {
        try {
            postService.InsertPost(post, userid);
            return RestResponse.createSuccessResponse(postRepository.save(post));
        } catch (ResourceNotFoundException e) {
            return RestResponse.createFailureResponse(e.getMessage(), 400);
        }
    }

    @PutMapping("/posts/{postId}")
    @ApiOperation(value = "Update a post based on the post id.")
    public RestResponse<?> updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
        try {
            return RestResponse.createSuccessResponse(postService.EditPost(postId, postRequest));
        } catch (ResourceNotFoundException e) {
            return RestResponse.createFailureResponse(e.getMessage(), 400);
        }
    }

    @DeleteMapping("/posts/{postId}")
    @ApiOperation(value = "Delete a post based on the post id.")
    public RestResponse<?> deletePostOfUser(@PathVariable Long postId) {
        try {
            return RestResponse.createSuccessResponse(postService.deletePost(postId));
        } catch (ResourceNotFoundException e) {
            return RestResponse.createFailureResponse(e.getMessage(), 400);
        }
    }
}

