package com.effitizer.start.service.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Setter;

@Service
public class SchedulePaymentService {
    @Setter(onMethod_ = @Autowired)
    private ImportPayService pay;

    public String schedulePay(long customer_uid, int price) {
        String token = pay.getToken();
        long timestamp = 0;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.KOREA);
        cal.add(Calendar.MINUTE, +1);
        String date = sdf.format(cal.getTime());
        try {
            Date stp = sdf.parse(date);
            timestamp = stp.getTime()/1000;
            System.out.println(timestamp);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Gson str = new Gson();
        token = token.substring(token.indexOf("response") +10);
        token = token.substring(0, token.length() - 1);
        GetTokenVO vo = str.fromJson(token, GetTokenVO.class);
        String access_token = vo.getAccess_token();


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(access_token);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("merchant_uid", timestamp);
        jsonObject.addProperty("schedule_at", timestamp);
        jsonObject.addProperty("amount", price);

        JsonArray jsonArr = new JsonArray();

        jsonArr.add(jsonObject); JsonObject reqJson = new JsonObject();

        reqJson.addProperty("customer_uid", customer_uid);
        reqJson.add("schedules",jsonArr);
        String json = str.toJson(reqJson);
        System.out.println(json);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        return restTemplate.postForObject("https://api.iamport.kr/subscribe/payments/schedule", entity, String.class);




    }
}
