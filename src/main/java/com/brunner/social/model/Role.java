package com.brunner.social.model;

public enum Role {
    ADMIN, CLIENT;

    public String getAuthority() {
        return name();
    }
}
