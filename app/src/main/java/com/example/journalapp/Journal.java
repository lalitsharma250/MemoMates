package com.example.journalapp;

import com.google.firebase.Timestamp;

public class Journal {
    private String title;
    private String thoughts ;
    private String imageUrl;
    private Timestamp timeAdded;
    String userName,userId;

    public Journal(String title, String thoughts, String imageUrl, Timestamp timeAdded, String userName, String userId) {
        this.title = title;
        this.thoughts = thoughts;
        this.imageUrl = imageUrl;
        this.timeAdded = timeAdded;
        this.userName = userName;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThoughts() {
        return thoughts;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Timestamp getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(Timestamp timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Journal() {
    }


}
