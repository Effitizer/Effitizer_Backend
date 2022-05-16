package com.effitizer.start.service;

import com.effitizer.start.aws.S3Uploader;
import com.effitizer.start.domain.*;
import com.effitizer.start.domain.dto.Contents.AllContentsDTO;
import com.effitizer.start.domain.dto.Contents.ContentsDTO;
import com.effitizer.start.domain.dto.Contents.ContentsRequest;
import com.effitizer.start.repository.ContentsRepository;
import com.effitizer.start.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class ContentsService {
    @Autowired ContentsRepository contentsRepository;
    @Autowired OrderService orderService;
    @Autowired S3Uploader s3Uploader;

    /**
     * 콘텐츠 저장
     */
    public Contents save(Contents contents) {
        contentsRepository.save(contents);
        return contents;
    }

    /**
     *  콘텐츠 id로 검색
     */
    public Contents findContensById(Long contents_id) {
        return contentsRepository.findById(contents_id)
                .orElse(null);
    }

    public List<ContentsDTO> saveContents(LinkedList<ContentsRequest> contentsRequestLinkedList, User user, Book book) {
        List<ContentsDTO> contentsDTOArrayList = new ArrayList<>();
        for (int i=0; i<=contentsRequestLinkedList.size(); i++){
            ContentsRequest contentsRequest = contentsRequestLinkedList.get(i);
            Contents contents = new Contents(user, book, contentsRequest.getTitle(), contentsRequest.getContent());
            contentsRepository.save(contents);

            // Contentsfile 생성
            Contentsfile contentsfile = s3Uploader.upload(contents.getId(), contentsRequest.getImage(), "image");

            //Order 생성
            Order order = orderService.saveOrder(contents, contentsRequest.getOrder());

            //DTO 생성
            ContentsDTO contentsDTO = new ContentsDTO(contents);
            contentsDTOArrayList.add(contentsDTO);
        }
        return contentsDTOArrayList;
    }
}
