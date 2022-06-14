package com.effitizer.start.service.Payment;

import com.effitizer.start.domain.Payment;
import com.effitizer.start.domain.Subscribe;
import com.effitizer.start.domain.dto.Payment.Request.PaymentRegularRequest;
import com.effitizer.start.domain.dto.Payment.Request.PaymentRequest;
import com.effitizer.start.repository.PaymentRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minidev.json.JSONObject;
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
    public Payment saveRegularPayment(Subscribe subscribe, JSONObject newPaymentData) {
        // 이전 결제 정보 가져오기
        Payment prePayment = paymentRepository.findBySubscribe(subscribe).get(0);
        String imp_uid = (String) newPaymentData.get("imp_uid");
        String merchant_uid = (String) newPaymentData.get("merchant_uid");

        // 새로운 결제 정보 저장
        Payment payment = Payment.builder()
                        .prePayment(prePayment)
                        .subscribe(subscribe)
                        .imp_uid(imp_uid)
                        .merchant_uid(merchant_uid)
                        .build();
        paymentRepository.save(payment);
        return payment;
    }
}
