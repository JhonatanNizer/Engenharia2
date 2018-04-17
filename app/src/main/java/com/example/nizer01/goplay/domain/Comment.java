package com.example.nizer01.goplay.domain;

public class Comment {

    private String title;
    private String description;
    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

}
