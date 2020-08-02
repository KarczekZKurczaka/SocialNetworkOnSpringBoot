package com.brunner.social.model;


import javax.persistence.Entity;

@Entity
public class Post {
    private Long id;
    private String title;
    private String description;
    private String content;

    private UserProfile userProfile;
}

