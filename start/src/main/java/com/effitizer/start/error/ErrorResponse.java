package com.effitizer.start.error;

import lombok.*;

@Data
public class ErrorResponse {
    private int statusCode;
    private String message;
    private long timestamp;

    public ErrorResponse (int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

}
