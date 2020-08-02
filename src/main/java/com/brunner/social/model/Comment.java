package com.brunner.social.model;


import javax.persistence.Entity;

@Entity
public class Comment {
    private Long id;
    private String text;
    private Post post;
    private UserProfile userProfile;
}

