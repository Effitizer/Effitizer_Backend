package com.effitizer.start.controller;

import com.effitizer.start.domain.Book;
import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Group;
import com.effitizer.start.domain.dto.Group.AllGroupDTO;
import com.effitizer.start.domain.dto.Group.Book.GroupBookDTO;
import com.effitizer.start.domain.dto.Group.Contents.GroupContentsDTO;
import com.effitizer.start.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("test/api/group")
public class GroupController {
    @Autowired
    GroupService groupService;

    /**
     * 컨텐츠
     */
    @GetMapping("/api/group")
    public ResponseEntity<List<?>> sendAllGroupList() {
        try {
            log.info("Group controller: /api/group ---------------------");
            List<Group> groupList= groupService.findAllGroupList();
            List<AllGroupDTO> allGroupDTOList = new ArrayList<>();
            for(int i=0; i<=groupList.size(); i++) {
                Group group = groupList.get(i);
                List<Contents> contentsList = group.getContents();
                List<GroupContentsDTO> groupContentsDTOList = new ArrayList<>();
                for(int j=0; j<=contentsList.size();j++) {
                    Contents contents = contentsList.get(i);
                    //Content 설정
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
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }
}
