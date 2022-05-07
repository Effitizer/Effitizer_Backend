package com.effitizer.start.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class ApiError {
    @Autowired String message;
    @Autowired int status;

    ApiError(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

}
