package com.effitizer.start.domain.dto.Subscribe;

import com.effitizer.start.domain.Subscribe;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SubscribeDTO {
    private Long id; //  id

    private LocalDateTime start_date; //구독시작일
    private LocalDateTime expired_date; //만료일
    private LocalDateTime canceled_data; // 결제 중단일

    public SubscribeDTO(Long id, Long user_id, LocalDateTime start_date, LocalDateTime expired_date, LocalDateTime canceled_data){
        this.id=id;
        this.user_id=user_id;
        this.start_date=start_date;
        this.expired_date=expired_date;
        this.canceled_data=canceled_data;
    }

    public SubscribeDTO(Subscribe subscribe) {
        this.id=subscribe.getId();
        this.user_id=subscribe.getUser().getId();
        this.start_date=subscribe.getStart_date();
        this.expired_date=subscribe.getExpired_date();
        this.canceled_data=subscribe.getCanceled_data();
    }
}
