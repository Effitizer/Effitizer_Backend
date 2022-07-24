package com.effitizer.start.service;

import com.effitizer.start.domain.Publisher;
import com.effitizer.start.repository.PublisherRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PublisherServiceTest {
    @Autowired
    PublisherRepository publisherRepository;
    @Autowired PublisherService publisherService;

    @Test
    public void 출판사저장() {
        // Given
        Publisher publisher = createPublisher("publisher1");

        // When
        publisherService.savePublisher(publisher.getName());

        // Then
        fail("중복 데이터 에러가 발생한다.");
    }

    @Test
    public void 출판사수정저장확인() {
        // Given
        Publisher publisher1 = createPublisher("publisher1");
        String name = "publisher2"

        // When
        publisherService.editPublisher(name, publisher1.getId());

        // Then
        assertEquals("출판사 데이터 수정 확인", publisher1.getName(), name);
    }

    @Test
    public void 출판사수정중복데이터확인() {
        // Given
        Publisher publisher1 = createPublisher("publisher1");
        Publisher publisher2 = createPublisher("publisher2");

        // When
        publisherService.editPublisher(publisher2.getName(), publisher1.getId());

        // Then
        fail("중복 데이터 에러가 발생한다.");
    }

    private Publisher createPublisher(String name) {
        Publisher publisher = new Publisher(name);
        publisherRepository.save(publisher);
        return publisher;
    }
}
