package com.example.nizer01.goplay.domain;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Event {

    private String id;
    private String uid;
    private String name;
    private String description;
    private String requirements;
    private String investiments;
    private Long startTime;
    private Long duration;
    private Long endTime;
    private String status;
    private int maxPlayers;
    private int minPlayers;
    private Activity activity;
    private Local local;

    private ArrayList<Profile> profiles = new ArrayList<>();
    private ArrayList<String> puids = new ArrayList<>();

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

    public Activity getActivity() { return activity; }

    public void setActivity(Activity activity) { this.activity = activity; }

    public Local getLocal() { return local; }

    public void setLocal(Local local) { this.local = local; }

    public String toString(){ return this.name; }

    public ArrayList<Profile> getProfiles() { return profiles; }

    public void setProfiles(ArrayList<Profile> profiles) { this.profiles = profiles; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvestiments() {
        return investiments;
    }

    public void setInvestiments(String investiments) {
        this.investiments = investiments;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPuids(ArrayList<String> puids) {
        this.puids = puids;
    }

    public void addPuid(String puid) {
        this.puids.add(puid);
    }

    public void removePuid(String puid) {
        this.puids.remove(puid);
    }

    public ArrayList<String> getPuids() {
        return puids;
    }
}
