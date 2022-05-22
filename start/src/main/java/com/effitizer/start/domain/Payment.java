package com.effitizer.start.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    private Long id; //  id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subscribe_id")
    private Subscribe subscribe;

    private Long paid_amount; //결제 가격
    private String name; // 구매자 이름
    private String imp_uid; // 고유ID
    private Long apply_num; //카드승인번호
    private String card_name; // 카드 이름
    private Long card_num; // 카드 번호
    private String currency; // 통화
    private String receipt_url; // 영수증URL
    private boolean is_refunded; // 환불 여부

}
