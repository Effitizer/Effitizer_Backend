package com.effitizer.start.service;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Contentsfile;
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

@Service
@Transactional
public class SubscribeService {
    @Autowired
    SubscribeRepository subscribeRepository;

    public List<Subscribe> findSubscribesByUserId(Long user_id) {
        return subscribeRepository.findByUserId(user_id);
    }
    public Subscribe findSubscribeByUserId(Long user_id) {
        return subscribeRepository.findFirstByUserIdOrderByIdDesc(user_id)
                .orElse(null);
    }
    public void saveOne(User user) {

        LocalDateTime current = LocalDateTime.now();
        Subscribe subscribe =new Subscribe(user,current,current.plusMonths(1),current.plusMonths(1));
        subscribeRepository.save(subscribe);
    }

    public void updateOne(User user, LocalDateTime expired_date) {

        LocalDateTime current = LocalDateTime.now();
        Subscribe subscribe =new Subscribe(user,current,expired_date.plusMonths(1),expired_date.plusMonths(1));
        subscribeRepository.save(subscribe);
    }

//    public Contents saveOne(Contents contents) {
//        userRepository.save(contents.getUser());
//        contentsRepository.save(contents);
//        contents.setOrder(orderService.saveOrder(contents, 1));
//        Contentsfile contentsfile = contentsfileRepository.save(new Contentsfile());
//        List<Contentsfile> contentsfileList = new ArrayList<>();
//        contentsfileList.add(contentsfile);
//        contents.setContentsfiles(contentsfileList);
//        return contents;
//    }
}
