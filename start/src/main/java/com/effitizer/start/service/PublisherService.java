package com.effitizer.start.service;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Order;
import com.effitizer.start.domain.Publisher;
import com.effitizer.start.domain.User;
import com.effitizer.start.domain.dto.Contents.Request.OnlyContentsRequest;
import com.effitizer.start.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class PublisherService {
    @Autowired PublisherRepository publisherRepository;

    public Publisher saveOne(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    /**
     * id로 출판자 조회
     */
    public Publisher findPublisherById(Long publisherId) {
        return publisherRepository.findById(publisherId)
                .orElseThrow(() -> new IllegalStateException("출판사 정보가 올바르지 않습니다."));
    }

    /**
     * 출판사 저장
     */
    public Publisher savePublisher(String publisherName) {
        // 중복 데이터 검사
        publisherRepository.findByName(publisherName)
                .ifPresent(p -> {
                    log.info("CategoryService: 중복 데이터 ---------------------");
                    throw new IllegalStateException("이미 존재하는 데이터입니다.");
                });

        Publisher publisher = new Publisher(publisherName);
        publisherRepository.save(publisher);
        return publisher;
    }

    public Publisher editPublisher(String publisherName, Long id) {
        // 중복 데이터 검사
         publisherRepository.findByName(publisherName)
                .ifPresent(p -> {
                    log.info("CategoryService: 중복 데이터 ---------------------");
                    throw new IllegalStateException("이미 존재하는 데이터입니다.");
                });
         Publisher publisher= publisherRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("출판사 정보가 올바르지 않습니다."));
         publisher.setName(publisherName);
         return publisher;
    }

    public Publisher savePublisherOrFind(String publisher_name) {
        Optional<Publisher> findPublisher = publisherRepository.findByName(publisher_name);
        if (findPublisher.isEmpty()) {
            Publisher publisher = new Publisher(publisher_name);
            publisherRepository.save(publisher);
            return publisher;
        }
        return findPublisher.get();
    }

    public List<Publisher> findAll(){
        return publisherRepository.findAll();
    }


}
