package com.effitizer.start.service.Payment;

import com.effitizer.start.domain.Payment;
import com.effitizer.start.domain.Subscribe;
import com.effitizer.start.domain.dto.Payment.Request.PaymentRequest;
import com.effitizer.start.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired PaymentRepository paymentRepository;

    /**
     * Payment 저장
     */
    public Payment savePayment(PaymentRequest paymentRequest, Subscribe subscribe) {
        Payment payment = new Payment(paymentRequest, subscribe);
        paymentRepository.save(payment);
        return payment;
    }
}
