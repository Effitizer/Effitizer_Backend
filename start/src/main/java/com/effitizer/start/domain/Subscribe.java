package com.effitizer.start.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Subscribe{
    @Id
    @GeneratedValue
    @Column(name = "subscribe_id")
    private Long id; //  id

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 회원 id

    @OneToMany(mappedBy = "subscribe", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    private LocalDateTime start_date; //구독시작일
    private LocalDateTime expired_date; //만료일
    private LocalDateTime canceled_data; // 결제 중단일

    //==연관관계 메서드==//
    public void setUser(User user) {
        this.user = user;
        user.setSubscribe(this);
    }

    public void setPayment(Payment payment) {
        this.payments.add(payment);
        payment.setSubscribe(this);
    }
}
