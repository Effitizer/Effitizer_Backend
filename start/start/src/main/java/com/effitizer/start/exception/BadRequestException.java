package com.effitizer.start.exception;

public class BadRequestException extends RuntimeException {
    private String errorCode;

    /**
     * Error Code 지정
     * @param code
     */
    public BadRequestException(String code) {
        this.errorCode = code;
    }

    public String getErrorCode() {
        return errorCode;
    }
}