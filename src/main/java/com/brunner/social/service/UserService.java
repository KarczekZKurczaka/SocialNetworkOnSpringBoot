package com.brunner.social.service;

import com.brunner.social.exception.CustomException;
import com.brunner.social.model.User;
import com.brunner.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String insertUser(User user) {
        boolean flag;

        flag = alreadyRegistered(user.getEmail());
        if (flag != true) {
            userRepository.save(user);
            return String.format("User %s created", user.getEmail());
        } else {
            throw new CustomException("User is already registered", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public User getUser(String email) {
        User user;
        user = userRepository.findByEmail(email);
        return user;
    }

    public boolean alreadyRegistered(String email) {
        return userRepository.existsByEmail(email);
    }
}
