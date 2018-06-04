package com.example.nizer01.goplay.domain;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Event {

    private String name;
    private String description;
    private String requirements;
    private String cost;
    private Long startTime;
    private Long duration;
    private Long endTime;
    private String status;
    private int maxPlayers;
    private int minPlayers;
    private Activity activity;
    private Local local;

    private ArrayList<Profile> profiles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getDuration() { return duration; }

    public void setDuration(Long duration) { this.duration = duration; }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public String getInvestiments() {
        return cost;
    }

    public void setInvestiments(String cost) {
        this.cost = cost;
    }

    public Activity getActivity() { return activity; }

    public void setActivity(Activity activity) { this.activity = activity; }

    public Local getLocal() { return local; }

    public void setLocal(Local local) { this.local = local; }

    public String toString(){ return this.name; }

    public ArrayList<Profile> getProfiles() { return profiles; }

    public void setProfiles(ArrayList<Profile> profiles) { this.profiles = profiles; }

}
