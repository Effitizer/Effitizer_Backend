package com.effitizer.start.service;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.repository.ContentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContentsService {
    @Autowired ContentsRepository contentsRepository;

    public Contents save(Contents contents) {
        contentsRepository.save(contents);
        return contents;
    }
}
