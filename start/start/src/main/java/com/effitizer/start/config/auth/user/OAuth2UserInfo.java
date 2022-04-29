package com.effitizer.start.config.auth.user;

import java.util.Map;

public abstract class OAuth2UserInfo {
    // provider에 따라 각각 정보가 다른 이름이 들어옴.
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getFirstName();

    public abstract String getLastName();
}
