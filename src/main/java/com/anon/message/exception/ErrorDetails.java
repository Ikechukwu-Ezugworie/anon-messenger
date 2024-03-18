package com.anon.message.exception;

public class ErrorDetails {

    private String message;
    private String details;

    private String status;

    public ErrorDetails() {
    }

    public ErrorDetails(String message) {
        this.message = message;
    }

    public ErrorDetails(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public ErrorDetails(String message, String details, String status) {
        this.message = message;
        this.details = details;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
