package com.effitizer.start.service;

import com.effitizer.start.domain.Writer;
import com.effitizer.start.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class WriterService {
    @Autowired WriterRepository writerRepository;

    /**
     * Writer 이름 조회 후 없으면 생성 있으면 반환
     */
    public Writer saveWriterOrFind(String writer_name) {
        Optional<Writer> findWriter = writerRepository.findByName(writer_name);
        if (findWriter.isEmpty()) {
            Writer writer = new Writer(writer_name);
            writerRepository.save(writer);
            return writer;
        }
        return findWriter.get();
    }
}
