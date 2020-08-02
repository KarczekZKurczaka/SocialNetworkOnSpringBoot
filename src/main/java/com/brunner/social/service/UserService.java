package com.brunner.social.service;

import com.brunner.social.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String insertUser(User user) {
        boolean flag;

        flag = alreadyRegistered(user.getEmail());

        return "TBD";
    }

    public String fetchUserToken(String email) {
        User user;
        boolean flag;

        flag = alreadyRegistered(email);
        return "TBD";
    }

    public User getUser(String email) {
        return new User();
    }

    public boolean alreadyRegistered(String email) {
        return false;
    }
}
