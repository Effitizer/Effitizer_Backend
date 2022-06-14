package com.effitizer.start.domain;

import com.effitizer.start.domain.dto.Payment.Request.PaymentRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    private String name;
    private String imp_uid; // 고유ID
    private int paid_amount; //결제 가격
    private Long apply_num; //카드승인번호
    private String card_name; // 카드 이름
    private String card_num; // 카드 번호
    private String currency; // 통화
    private String receipt_url; // 영수증URL
    private boolean is_refunded; // 환불 여부

    // 추가 데이터
    private String customer_uid;
    private String  pg_provider;
    private Date pay_date;

    public Payment(PaymentRequest paymentRequest, Subscribe subscribe) {
        this.imp_uid = paymentRequest.getImp_uid();
        this.name = paymentRequest.getBuyer_name();
        this.paid_amount = paymentRequest.getPaid_amount();
        this.apply_num = paymentRequest.getApply_num();
        this.card_name = paymentRequest.getCard_name();
        this.card_num = paymentRequest.getCard_num();
        this.currency = paymentRequest.getCurrency();
        this.receipt_url = paymentRequest.getReceipt_url();
        this.customer_uid = paymentRequest.getCustomer_uid();
        this.pg_provider = paymentRequest.getPg_provider();
        this.pay_date = paymentRequest.getPay_date();
        this.is_refunded = false;

        this.setSubscribe(subscribe);
    }

    //==연관관계 메서드==//
    public void setSubscribe(Subscribe subscribe) {
        this.subscribe = subscribe;
        subscribe.getPayments().add(this);
    }

}
