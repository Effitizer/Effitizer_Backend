package com.effitizer.start.config.auth.dto;

import com.effitizer.start.domain.User;
import lombok.Getter;

@Getter
public class SessionUser {
    private String name;
    private String email;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
