package com.effitizer.start.service;

import com.effitizer.start.domain.Category;
import com.effitizer.start.domain.Publisher;
import com.effitizer.start.domain.Writer;
import com.effitizer.start.repository.PublisherRepository;
import com.effitizer.start.repository.WriterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;
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

    public Publisher savePublisherOrFind(String publisher_name) {
        Optional<Publisher> findPublisher = publisherRepository.findByName(publisher_name);
        if (findPublisher.isEmpty()) {
            Publisher publisher = new Publisher(publisher_name);
            publisherRepository.save(publisher);
            return publisher;
        }
        return findPublisher.get();
    }
}
