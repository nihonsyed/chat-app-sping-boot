package com.example.chatapp.enums.utils;

public enum LogMessages {
    APPLICATION_START("Application started successfully."),
    APPLICATION_START_FAILURE("Application failed to start."),
    EXCEPTION_OCCURRED("An exception occurred.");

    private final String description;

    LogMessages(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
