package com.brunner.social.controllers;


import com.brunner.social.RestResponse;
import com.brunner.social.exception.ResourceNotFoundException;
import com.brunner.social.model.Comment;
import com.brunner.social.model.User;
import com.brunner.social.repository.CommentRepository;
import com.brunner.social.repository.PostRepository;
import com.brunner.social.repository.UserRepository;
import com.brunner.social.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentService commentService;

    public Page<Comment> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId,
                                                Pageable pageable) {

        return commentRepository.findByPostId(postId, pageable);
    }

    @PostMapping("/posts/{postId}/{userId}/comments")
    public RestResponse<?> createComment(@PathVariable(value = "postId") Long postId,
                                         @Valid @RequestBody Comment comment, @PathVariable(value = "userId") Long userId) {

        Optional<User> user = userRepository.findById(userId);
        comment.setUser(user.get());
        try {
            return RestResponse.createSuccessResponse(commentService.saveComment(postId, comment));
        } catch (ResourceNotFoundException e) {
            return RestResponse.createFailureResponse(e.getMessage(), 400);
        }

    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public RestResponse<?> updateComment(@PathVariable(value = "postId") Long postId,
                                         @PathVariable(value = "commentId") Long commentId,
                                         @Valid @RequestBody Comment commentRequest) {

        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        try {
            return RestResponse.createSuccessResponse(commentService.editComment(commentId, commentRequest));
        } catch (ResourceNotFoundException e) {
            return RestResponse.createFailureResponse(e.getMessage(), 400);
        }

    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public RestResponse<?> deleteComment(@PathVariable(value = "postId") Long postId,
                                         @PathVariable(value = "commentId") Long commentId) {

        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }

        try {
            return RestResponse.createSuccessResponse(commentService.deleteComment(commentId));
        } catch (ResourceNotFoundException e) {
            return RestResponse.createFailureResponse(e.getMessage(), 400);
        }
    }
}

