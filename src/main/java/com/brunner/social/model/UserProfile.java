package com.brunner.social.model;

import javax.persistence.Entity;

@Entity
public class UserProfile {
    private Long Id;
    private String about;
    private String profilePicUrl;
    private boolean gender;
    private String location;
    private String interests;
    private String languages;
    private User user;
}
