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


    public Contents save(Contents contents) {
        contentsRepository.save(contents);
        return contents;
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
            ContentsDTO contentsDTO = new ContentsDTO(contents.getId(), contents.getUser().getId(), order.getOrder_num(), contents.getTitle(), contents.getContent(), book.getCategory().getName(), contentsfile.getPath());
            contentsDTOArrayList.add(contentsDTO);
        }
        return contentsDTOArrayList;
    }
}
