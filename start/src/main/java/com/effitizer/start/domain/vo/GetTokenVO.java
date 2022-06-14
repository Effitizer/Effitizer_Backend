package com.effitizer.start.domain.vo;

import lombok.Data;

@Data
public class GetTokenVO {
    private String access_token;
    private long now;
    private long expired_at;
}
