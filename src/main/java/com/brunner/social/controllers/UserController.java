package com.brunner.social.controllers;

import com.brunner.social.RestResponse;
import com.brunner.social.dto.PostDto;
import com.brunner.social.exception.CustomException;
import com.brunner.social.exception.ResourceNotFoundException;
import com.brunner.social.model.User;
import com.brunner.social.repository.PostRepository;
import com.brunner.social.repository.UserRepository;
import com.brunner.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/users/signup")
    public RestResponse createUser(@Valid @RequestBody User user) {

        try {
            return RestResponse.createSuccessResponse(userService.insertUser(user));
        } catch (CustomException e) {
            return RestResponse.createFailureResponse(e.getMessage(), 400);
        }
    }

    @GetMapping("/users/signin")
    public RestResponse getUser(@Valid @RequestParam("email") String email) {

        try {
            return RestResponse.createSuccessResponse(userService.getUser(email));
        } catch (ResourceNotFoundException e) {
            return RestResponse.createFailureResponse(e.getMessage(), 400);
        }
    }


    @GetMapping("/users/getall")
    public List<PostDto> getAllPosts(@Valid @RequestParam("userId") Long userId) {

        return postRepository.findByUserId(userId);
    }
}
