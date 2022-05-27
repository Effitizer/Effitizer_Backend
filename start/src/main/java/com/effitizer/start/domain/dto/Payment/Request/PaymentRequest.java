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
    private String customer_uid;
    private String imp_uid;
    private String merchant_uid;
    private String buyer_email;
    private Date pay_date;
    private int paid_amount;
    private String card_number;
}
