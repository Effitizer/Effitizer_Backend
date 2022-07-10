package com.effitizer.start.domain.dto.Subscribe.Request;

import jdk.vm.ci.meta.Local;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SubscribeRequest {
    private Long user_id;
    private LocalDateTime start_date;
    private LocalDateTime expired_date;
    private LocalDateTime canceled_date;
}
