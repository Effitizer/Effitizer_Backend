package com.effitizer.start.controller;

import com.effitizer.start.component.ReqPaymentScheduler;
import com.effitizer.start.domain.dto.Group.GroupDTO;
import com.effitizer.start.domain.dto.Payment.Request.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
public class PaymentController {
    @Autowired ReqPaymentScheduler scheduler;

    @GetMapping("/test/pay")
    public String goPay(Model model) {
        log.info("------------- payment controller -> pay");
        return "payTest";
    }

    @PostMapping("/test/insertSubscribe")
    @ResponseBody
    public int getPayment(@RequestBody PaymentRequest paymentRequest) {
        log.info("------------- payment controller -> insertSubscribe");
        log.info("------------- payment controller -> " + paymentRequest.getMerchant_uid());
        int res = 1;
        return res;
    }

    @PostMapping("/test/payment1")
    @ResponseBody
    public void getImportToken(@RequestBody PaymentRequest paymentRequest)
            throws JsonMappingException, JsonProcessingException {
        log.info("------------- payment controller -> payment1");
        String customer_uid = paymentRequest.getCustomer_uid();
        int price = paymentRequest.getPaid_amount();
        long merchant_uid = Long.parseLong(paymentRequest.getMerchant_uid());
        log.info("-------------"+ customer_uid);
        log.info("-------------"+ price);
        scheduler.startScheduler(customer_uid, price);
    }
}
