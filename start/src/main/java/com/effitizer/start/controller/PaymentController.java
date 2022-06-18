package com.effitizer.start.controller;

import com.effitizer.start.component.ReqPaymentScheduler;
import com.effitizer.start.config.auth.dto.SessionUser;
import com.effitizer.start.domain.Payment;
import com.effitizer.start.domain.Subscribe;
import com.effitizer.start.domain.User;
import com.effitizer.start.domain.dto.Group.GroupDTO;
import com.effitizer.start.domain.dto.Payment.PaymentDTO;
import com.effitizer.start.domain.dto.Payment.Request.PaymentRequest;
import com.effitizer.start.domain.dto.Subscribe.SubscribeDTO;
import com.effitizer.start.repository.PaymentRepository;
import com.effitizer.start.service.Payment.PaymentService;
import com.effitizer.start.service.SubscribeService;
import com.effitizer.start.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/test/api/payment")
public class PaymentController {
    @Autowired HttpSession httpSession;
    @Autowired ReqPaymentScheduler scheduler;
    @Autowired UserService userService;
    @Autowired SubscribeService subscribeService;
    @Autowired PaymentService paymentService;

    /**
     * 테스트용 결제 페이지 이동
     */
    @GetMapping("/pay")
    public String goPay(Model model) {
        log.info("------------- payment controller -> pay");
        return "payTest";
    }

    /**
     * 결제 데이터 생성 및 결제에 대한 구독 생성
     */
    @PostMapping("/new")
    @ResponseBody
    public ResponseEntity<?> savePayment(@RequestBody PaymentRequest paymentRequest) {
        log.info("Payment controller: /payment/new ---------------------");
        // 구독 데이터 생성
        User user = userService.findUserById(paymentRequest.getUser_id());
        Subscribe subscribe = subscribeService.saveSubscribe(user);

        // 결제 데이터 생성
        Payment payment = paymentService.savePayment(paymentRequest, subscribe);
        PaymentDTO paymentDTO = new PaymentDTO(payment);

        // 정기 결제를 위한 스케줄러 생성
        scheduler.startScheduler(paymentRequest.getCustomer_uid(), paymentRequest.getPaid_amount());
        return ResponseEntity.ok(paymentDTO);
    }

    /**
     * 정기결제 예약 시스템으로 결제 완료 시
     */
    @PostMapping("/regular")
    public void saveRegularPayment(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        log.info("Payment controller: /payment/regular---------------------");
        // Session
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        // Subscribe 정보 업데이트
        Subscribe subscribe = subscribeService.updateSubscribe(user.getEmail());

        // 새로운 결제 데이터 생성
        Payment payment = paymentService.saveRegularPayment(subscribe, jsonObject);

    }

}
