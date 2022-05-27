package com.effitizer.start.service.Payment;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;

@Service
public class ImportPayService {

    public String getToken() {

        RestTemplate restTemplate = new RestTemplate();

        //서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        Map<String, Object> map = new HashMap<>();
        // 아임포트 시스템 설정에서 확인할 수 있는 데이터
        map.put("imp_key", "2589352930819343");
        map.put("imp_secret", "e0a2352d4e087a9db24ae347b476914b047c01266a23b3f87cf308cdcd50418995287ffdd78eb1fa");


        Gson var = new Gson();
        String json=var.toJson(map);
        //서버로 요청할 Body

        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        return restTemplate.postForObject("https://api.iamport.kr/users/getToken", entity, String.class);
    }
}
