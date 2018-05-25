package com.example.nizer01.goplay.domain;

public class Notification {

    private String title;
    private String description;
    private Profile profile;
    private Event event;

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

    public Profile getProfile() { return profile; }

    public void setProfile(Profile profile) { this.profile = profile; }

    public Event getEvent() { return event; }

    public void setEvent(Event event) { this.event = event; }

}
