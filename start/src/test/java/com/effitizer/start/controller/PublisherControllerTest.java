package com.effitizer.start.controller;

import com.effitizer.start.domain.Book;
import com.effitizer.start.domain.Publisher;
import com.effitizer.start.repository.PublisherRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PublisherControllerTest {
    @Autowired
    PublisherRepository publisherRepository;

    @Test
    public void 출판사생성() {
        // Given
        Publisher publisher = createPublisher("publisher1");

        // When


        // Then

    }


    private Publisher createPublisher(String name) {
        Publisher publisher = new Publisher(name);
        publisherRepository.save(publisher);
        return publisher;
    }
}
