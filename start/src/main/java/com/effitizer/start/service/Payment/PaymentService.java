package com.effitizer.start.service.Payment;

import com.effitizer.start.domain.Payment;
import com.effitizer.start.domain.Subscribe;
import com.effitizer.start.domain.dto.Payment.Request.PaymentRegularRequest;
import com.effitizer.start.domain.dto.Payment.Request.PaymentRequest;
import com.effitizer.start.repository.PaymentRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    /**
     *  정기결제 데이터 저장
     */
    public Payment saveRegularPayment(String scheduleData) {
        // stirng을 json 데이터로 변경
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        PaymentRegularRequest paymentRegularRequest = gson.fromJson(scheduleData, PaymentRegularRequest.class);

        Payment payment = new Payment();
        return payment;
    }
}
