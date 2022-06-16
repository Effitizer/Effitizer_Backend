package com.effitizer.start.service;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Contentsfile;
import com.effitizer.start.domain.Subscribe;

import com.effitizer.start.domain.Subscribe;

import com.effitizer.start.domain.User;
import com.effitizer.start.repository.SubscribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;


@Service
@Transactional
public class SubscribeService {

    @Autowired
    SubscribeRepository subscribeRepository;

    //user 아이디로 구독 정보 기록 조회
    public List<Subscribe> findSubscribesByUserId(Long user_id) {
        return subscribeRepository.findByUserId(user_id);
    }

    //user 아이디로 구독 정보 조회
    public Subscribe findSubscribeByUserId(Long user_id) {
        return subscribeRepository.findFirstByUserIdOrderByIdDesc(user_id)
                .orElse(null);
    }
    //user 구독 추가
    public void saveOne(User user) {

        LocalDateTime current = LocalDateTime.now();
        Subscribe subscribe =new Subscribe(user,current,current.plusMonths(1),current.plusMonths(1));
        subscribeRepository.save(subscribe);
    }
    //user 구독 연장
    public void updateOne(User user, LocalDateTime expired_date) {

        LocalDateTime current = LocalDateTime.now();
        Subscribe subscribe =new Subscribe(user,current,expired_date.plusMonths(1),expired_date.plusMonths(1));
        subscribeRepository.save(subscribe);
    }


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

    /**
    * id로 Subscribe 조회
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
