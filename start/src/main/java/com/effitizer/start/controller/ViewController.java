package com.effitizer.start.controller;

import com.effitizer.start.config.auth.dto.SessionUser;
import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.User;
import com.effitizer.start.domain.View;
import com.effitizer.start.domain.dto.Contents.ContentsDTO;
import com.effitizer.start.domain.dto.Group.GroupDTO;
import com.effitizer.start.domain.dto.User.UserDTO;
import com.effitizer.start.domain.dto.View.ViewDTO;
import com.effitizer.start.error.ErrorResponse;
import com.effitizer.start.service.UserService;
import com.effitizer.start.service.ViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("api/view")
public class ViewController {
    @Autowired
    ViewService viewService;
    @Autowired
    UserService userService;
    @Autowired
    HttpSession httpSession;

    /**
     * contents에 대해서 view 조회
     */
    @GetMapping("/{contents_id}")
    public ResponseEntity<?> selectViewByContents(@PathVariable("contents_id") Long contents_id) {
        try {
            log.info("View controller: api/view/{contents_id}---------------------");

            // View 조회
            List<ViewDTO> viewDTOList = viewService.findViewByContents(contents_id)
                    .stream()
                    .filter(view->view!=null)
                    .map(ViewDTO::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(viewDTOList);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
