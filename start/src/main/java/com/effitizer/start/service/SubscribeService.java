package com.effitizer.start.service;

import com.effitizer.start.domain.Subscribe;
import com.effitizer.start.domain.User;
import com.effitizer.start.repository.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class SubscribeService {
    @Autowired SubscribeRepository subscribeRepository;

    /**
     * Subscribe 저장
     */
    public Subscribe saveSubscribe(User user) {
        LocalDateTime nowTime = LocalDateTime.now();
        Subscribe subscribe = Subscribe.builder()
                .user(user)
                .start_date(nowTime)
                .expired_date(nowTime.plusDays(30))
                .build();
        subscribeRepository.save(subscribe);
        return subscribe;
    }

    /*
    id로 Subscribe 조회
     */
    public Subscribe findSubscribeById(Long subscribe_id) {
        return subscribeRepository.findById(subscribe_id)
                .orElse(null);
    }

    /**
     * Subscribe 구독 중단시키기
     */
    public Subscribe updateExpire(Long subscribe_id){
        Subscribe subscribe = findSubscribeById(subscribe_id);
        subscribe.setCanceled_data(LocalDateTime.now());
        return subscribe;
    }
}
