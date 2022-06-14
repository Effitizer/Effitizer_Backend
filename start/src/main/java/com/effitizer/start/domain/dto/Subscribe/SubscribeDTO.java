package com.effitizer.start.domain.dto.Subscribe;

import com.effitizer.start.domain.Subscribe;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SubscribeDTO {
    private Long id;
    private LocalDateTime start_date;
    private LocalDateTime expired_date;
    private LocalDateTime canceled_date;

    public SubscribeDTO(Subscribe subscribe){
        this.id = subscribe.getId();
        this.start_date = subscribe.getStart_date();
        this.expired_date = subscribe.getExpired_date();
        this.canceled_date = subscribe.getCanceled_data();
    }
}
