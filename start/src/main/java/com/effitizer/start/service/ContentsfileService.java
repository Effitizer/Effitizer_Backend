package com.effitizer.start.service;

import com.effitizer.start.domain.Bookcoverfile;
import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Contentsfile;
import com.effitizer.start.repository.ContentsfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContentsfileService {
    @Autowired
    ContentsfileRepository contentsfileRepository;

    /**
     * contentsfile 저장
     */
    public Contentsfile saveContentsfile(Contents contents, String fileName, String uploadImageUrl, Long size, String extend, String origin_filename) {
        Contentsfile contentsfile = Contentsfile.builder()
                .contents(contents)
                .extend(extend)
                .name(fileName)
                .size(size)
                .path(uploadImageUrl)
                .real_name(origin_filename)
                .build();
        contentsfileRepository.save(contentsfile);
        return contentsfile;
    }
}
