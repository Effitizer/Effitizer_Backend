package com.effitizer.start.domain.dto.Payment.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    private Long user_id;
    private String imp_uid;
    private String buyer_name;
    private int paid_amount;
    private Long apply_num;
    private String card_name;
    private String card_num;
    private String currency;
    private String receipt_url;
    private String customer_uid;
    private String  pg_provider;
    private Date pay_date;
    private String merchant_uid;
}
