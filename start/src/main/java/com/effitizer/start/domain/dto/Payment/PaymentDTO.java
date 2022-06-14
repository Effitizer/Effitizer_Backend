package com.effitizer.start.domain.dto.Payment;

import com.effitizer.start.domain.Payment;
import com.effitizer.start.domain.dto.Payment.Subscribe.PaymentSubscribeDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class PaymentDTO {
    private Long payment_id;
    private String imp_uid;
    private int paid_amount;
    private String customer_uid;
    private String  pg_provider;
    private Date pay_date;
    private PaymentSubscribeDTO subscribe;

    public PaymentDTO (Payment payment) {
        this.payment_id = payment.getId();
        this.imp_uid = payment.getImp_uid();
        this.paid_amount = payment.getPaid_amount();
        this.customer_uid = payment.getCustomer_uid();
        this.pg_provider = payment.getPg_provider();
        this.pay_date = payment.getPay_date();
        this.subscribe = new PaymentSubscribeDTO(payment.getSubscribe());
    }

}
