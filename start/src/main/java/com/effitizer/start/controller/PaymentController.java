package com.effitizer.start.controller;

import com.effitizer.start.component.ReqPaymentScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Controller
public class PaymentController {
    @Autowired ReqPaymentScheduler scheduler;

    @PostMapping("/payment1")
    public @ResponseBody
    void getImportToken(@RequestParam Map<String, Object> map)
            throws JsonMappingException, JsonProcessingException {
        int customer_uid = Integer.parseInt((String) map.get("customer_uid"));
        int price = Integer.parseInt((String) map.get("price"));
        long merchant_uid =  Long.parseLong((String) map.get("merchant_uid"));

        if(getPayementStatus.paymentStatus(merchant_uid).equals("paid")) {
            scheduler.startScheduler(customer_uid,price);
        }
    }
}
