package com.effitizer.start.domain.dto.Payment.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRegularRequest {
    private String customer_uid;
    private String merchant_uid;
    private String imp_uid;
}
