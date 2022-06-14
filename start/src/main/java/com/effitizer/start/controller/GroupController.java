package com.effitizer.start.controller;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Group;
import com.effitizer.start.domain.dto.Group.AllGroupDTO;
import com.effitizer.start.domain.dto.Group.Contents.GroupContentsDTO;
import com.effitizer.start.domain.dto.Group.GroupDTO;
import com.effitizer.start.domain.dto.Group.Request.GroupRequest;
import com.effitizer.start.error.ErrorResponse;
import com.effitizer.start.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("api/group")
public class GroupController {
    @Autowired GroupService groupService;

    /**
     * 그룹 저장
     * ADMIN 만 가능
     */
    @PostMapping("/new")
    public ResponseEntity<?> saveGroup(@RequestBody GroupRequest groupRequest) {
        log.info("Group controller: /api/group/new ---------------------");
        try {
            Group group = groupService.saveGroup(groupRequest);
            log.info("Group controller: /api/group/new ---------------------"+ group.toString());
            List<Contents> contentsList = group.getContents();
            List<GroupContentsDTO> groupContentsDTOList = new ArrayList<>();
            for(int i=0; i<contentsList.size(); i++) {
                Contents contents = contentsList.get(i);
                //Content 설정
                groupContentsDTOList.add(new GroupContentsDTO(contents));
                log.info("Group controller: /api/group/new ---------------------"+ groupContentsDTOList.size());
            }
            return ResponseEntity.ok(new GroupDTO(group, groupContentsDTOList));
        }
        catch (IllegalStateException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * 전체 그룹 검색
     */
    @GetMapping("")
    public ResponseEntity<?> sendAllGroupList() {
        try {
            log.info("Group controller: /api/group ---------------------");
            List<Group> groupList = groupService.findAllGroupList();
            List<AllGroupDTO> allGroupDTOList = new ArrayList<>();
            for (int i = 0; i < groupList.size(); i++) {
                Group group = groupList.get(i);
                List<Contents> contentsList = group.getContents();
                List<GroupContentsDTO> groupContentsDTOList = new ArrayList<>();
                for (int j = 0; j < contentsList.size(); j++) {
                    //Content 설정
                    Contents contents = contentsList.get(j);
                    GroupContentsDTO groupContentsDTO = new GroupContentsDTO(contents);
                    groupContentsDTOList.add(groupContentsDTO);
                }
                // Group 설정
                AllGroupDTO allGroupDTO = new AllGroupDTO(group, groupContentsDTOList);
                allGroupDTOList.add(allGroupDTO);
            }
            return ResponseEntity.ok(allGroupDTOList);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
