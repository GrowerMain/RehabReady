package com.example.rehabready;

public class Timer {
    private long id;
    private long startTime;
    private String userInput;

    public Timer(long id, long startTime, String userInput) {
        this.id = id;
        this.startTime = startTime;
        this.userInput = userInput;
    }

    // Getters and setters for id, startTime, and userInput
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
