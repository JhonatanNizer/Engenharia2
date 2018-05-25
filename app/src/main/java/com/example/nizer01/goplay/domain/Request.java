package com.example.nizer01.goplay.domain;

import java.util.Date;

public class Request {

    private String message;
    private Date date;
    private int status;
    private Profile profile;
    private Event event;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Profile getProfile() { return profile; }

    public void setProfile(Profile profile) { this.profile = profile; }

    public Event getEvent() { return event; }

    public void setEvent(Event event) { this.event = event; }

}
