package com.effitizer.start.domain.dto.Payment.Subscribe;

import com.effitizer.start.domain.Subscribe;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PaymentSubscribeDTO {
    private Long subscribe_id;
    private LocalDateTime start_date;

    public PaymentSubscribeDTO (Subscribe subscribe) {
        this. subscribe_id = subscribe.getId();
        this.start_date = subscribe.getStartDate();
    }
}

