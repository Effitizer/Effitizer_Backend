package com.effitizer.start.controller;

import com.effitizer.start.component.ReqPaymentScheduler;
import com.effitizer.start.domain.*;
import com.effitizer.start.domain.dto.Contents.ContentsDTO;
import com.effitizer.start.domain.dto.Subscribe.SubscribeDTO;
import com.effitizer.start.error.ErrorResponse;
import com.effitizer.start.service.SubscribeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/test/api/subscribe")
public class SubscribeController {
    @Autowired SubscribeService subscribeService;
    @Autowired ReqPaymentScheduler scheduler;

    @GetMapping("/stop/{subscribe_id}")
    public ResponseEntity<?> expireSubscribe(@PathVariable("subscribe_id") Long subscribe_id) {
        log.info("Subscribe controller: api/subscribe/stop/{subscribe_id}---------------------");
        scheduler.stopScheduler();

        Subscribe subscribe = subscribeService.updateExpire(subscribe_id);
        return ResponseEntity.ok(new SubscribeDTO(subscribe));

    }
}
