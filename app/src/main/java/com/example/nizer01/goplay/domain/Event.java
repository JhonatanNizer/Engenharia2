package com.example.nizer01.goplay.domain;


import java.sql.Timestamp;
import java.util.ArrayList;

public class Event {

    private String name;
    private String description;
    private String requirements;
    private Timestamp startTime;
    private Timestamp duration;
    private Timestamp endTime;
    private int status;
    private int maxPlayers;
    private int minPlayers;
    private double investiments;
    private Activity activity;
    private Local local;
    private ArrayList<User> users = new ArrayList<User>();

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

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getDuration() { return duration; }

    public void setDuration(Timestamp duration) { this.duration = duration; }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public double getInvestiments() {
        return investiments;
    }

    public void setInvestiments(double investiments) {
        this.investiments = investiments;
    }

    public Activity getActivity() { return activity; }

    public void setActivity(Activity activity) { this.activity = activity; }

    public Local getLocal() { return local; }

    public void setLocal(Local local) { this.local = local; }

    public String toString(){ return this.name; }

    public ArrayList<User> getUsers() { return users; }

    public void setUsers(ArrayList<User> users) { this.users = users; }

}
