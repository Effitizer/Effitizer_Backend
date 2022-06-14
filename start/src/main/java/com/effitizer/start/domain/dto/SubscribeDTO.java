package com.effitizer.start.domain.dto;

import com.effitizer.start.domain.Category;
import com.effitizer.start.domain.Payment;
import com.effitizer.start.domain.Subscribe;
import com.effitizer.start.domain.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class SubscribeDTO {

    private Long id; //  id

    private Long user_id; // 회원 id

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
