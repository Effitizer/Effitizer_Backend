package com.effitizer.start.controller;

import com.effitizer.start.config.auth.CustomOauth2UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Controller
public class LoginController {
    @Autowired
    CustomOauth2UserService customOauth2UserService;


    @GetMapping("/naver")
    @ResponseBody
    public String naverOAuthRedirect(@RequestParam String code, @RequestParam String state) {
        // RestTemplate 인스턴스 생성
        RestTemplate rt = new RestTemplate();

        HttpHeaders accessTokenHeaders = new HttpHeaders();
        accessTokenHeaders.add("Content-type", "application/x-www-form-urlencoded");

        MultiValueMap<String, String> accessTokenParams = new LinkedMultiValueMap<>();
        accessTokenParams.add("grant_type", "authorization_code");
        accessTokenParams.add("client_id", "{client_id");
        accessTokenParams.add("client_secret", "{client_secret}");
        accessTokenParams.add("code" , code);	// 응답으로 받은 코드
        accessTokenParams.add("state" , state); // 응답으로 받은 상태

        HttpEntity<MultiValueMap<String, String>> accessTokenRequest = new HttpEntity<>(accessTokenParams, accessTokenHeaders);

        ResponseEntity<String> accessTokenResponse = rt.exchange(
                "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                accessTokenRequest,
                String.class
        );
        return "accessToken: " + accessTokenResponse.getBody();
    }

    /*
    public String logintest(Model model){
        // test -> 바로 login으로 이동
        log.info("--- login controller - / login_test -----------------------------------------");
        //{sub=109204077595265448214, name=이채영, given_name=채영, family_name=이, picture=https://lh3.googleusercontent.com/a/AATXAJwF8Q3bnq7NldVLllh4uR-aiuFj2ilJopwILxk4HQ=s96-c,
        // email=lchy0413@gmail.com, email_verified=true, locale=ko}
        Map<String, Object> loginData = new HashMap<String,Object>();
        loginData.put("sub", "109204077595265448214");
        loginData.put("name", "이채영");
        loginData.put("email", "lchy0413@gmail.com");
        loginData.put("email_verified", "true");
        oAuthAttributes.of("google", "sub", loginData);
        return "login_test";
    }*/
}
