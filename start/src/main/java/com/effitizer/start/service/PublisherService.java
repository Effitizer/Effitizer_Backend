package com.effitizer.start.service;

import com.effitizer.start.domain.Publisher;
import com.effitizer.start.domain.Writer;
import com.effitizer.start.repository.PublisherRepository;
import com.effitizer.start.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PublisherService {
    @Autowired
    PublisherRepository publisherRepository;

    public Publisher savePublisherOrFind(String publisher_name) {
        Optional<Publisher> findPublisher = publisherRepository.findByName(publisher_name);
        if (findPublisher.isEmpty()) {
            Publisher publisher = new Publisher();
            publisher.setName(publisher_name);
            publisherRepository.save(publisher);
            return publisher;
        }
        return findPublisher.get();
    }
}
