package com.effitizer.start.controller;

import com.effitizer.start.domain.Category;
import com.effitizer.start.domain.Publisher;
import com.effitizer.start.domain.dto.Category.CategoryDTO;
import com.effitizer.start.domain.dto.Category.Request.CategoryRequest;
import com.effitizer.start.domain.dto.Publisher.PublisherDTO;
import com.effitizer.start.domain.dto.Publisher.Request.PublisherRequest;
import com.effitizer.start.domain.dto.User.UserDTO;
import com.effitizer.start.error.ErrorResponse;
import com.effitizer.start.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/api/publisher")
public class PublisherController {
    @Autowired PublisherService publisherService;

    /**
     * 출판사 저장
     */
    @PostMapping("/new")
    public ResponseEntity<?> savePublisher(@RequestBody PublisherRequest publisherRequest) {
        try {
            log.info("Publisher controller: /api/publisher/new ---------------------");
            Publisher publisher = publisherService.savePublisher(publisherRequest.getName());
            return new ResponseEntity<>(new PublisherDTO(publisher), HttpStatus.OK);
        }
        catch (IllegalStateException e) {
            // 동일한 이름이 이미 존재할 경우
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> savePublisher() {
        try {
            log.info("Publisher controller: /api/publisher---------------------");
            List<PublisherDTO> publisherDTOList= publisherService.findAll()
                    .stream()
                    .filter(publisher -> publisher != null)
                    .map(PublisherDTO::new)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(publisherDTOList, HttpStatus.OK);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{publisher_id}")
    public ResponseEntity<?> viewPublisher(@PathVariable ("publisher_id") Long publisher_id) {
        try {
            log.info("Publisher controller: /api/publisher/{publisher_id}---------------------");
            Publisher publisher= publisherService.findPublisherById(publisher_id);
            return new ResponseEntity<>(new PublisherDTO(publisher), HttpStatus.OK);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/edit/{publisher_id}")
    public ResponseEntity<?> editPublisher(@PathVariable ("publisher_id") Long publisher_id,@RequestBody PublisherRequest publisherRequest)
    {
        try{
            Publisher publisher = publisherService.editPublisher(publisherRequest.getName(),publisher_id);
            return new ResponseEntity<>(new PublisherDTO(publisher), HttpStatus.OK);
        }
        catch(Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }


//    @PatchMapping("/{publisher_id}")
//    public ReponseEntity<?> delete



}
