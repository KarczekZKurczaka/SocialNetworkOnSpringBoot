package com.brunner.social.service;

import com.brunner.social.exception.ResourceNotFoundException;
import com.brunner.social.model.Comment;
import com.brunner.social.repository.CommentRepository;
import com.brunner.social.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    public Comment saveComment(Long postId, Comment comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("PostId %s not found", postId)));
    }

    public Comment editComment(Long commentId, Comment comment) {
        return commentRepository.findById(commentId).map(updatedComment -> {
            updatedComment.setText(comment.getText());
            return commentRepository.save(updatedComment);
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("CommentId %s not found", commentId)));
    }

    public ResponseEntity deleteComment(Long commentId) {
        return commentRepository.findById(commentId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(String.format("CommentId %s not found", commentId)));
    }
}
