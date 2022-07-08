package com.effitizer.start.controller;

import com.effitizer.start.config.auth.dto.SessionUser;
import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Group;
import com.effitizer.start.domain.Role;
import com.effitizer.start.domain.User;
import com.effitizer.start.domain.dto.Category.CategoryDTO;
import com.effitizer.start.domain.dto.Group.AllGroupDTO;
import com.effitizer.start.domain.dto.Group.Contents.GroupContentsDTO;
import com.effitizer.start.domain.dto.Group.GroupDTO;
import com.effitizer.start.domain.dto.Group.Request.GroupRequest;
import com.effitizer.start.error.ErrorResponse;
import com.effitizer.start.service.GroupService;
import com.effitizer.start.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("api/group")
public class GroupController {
    @Autowired GroupService groupService;
    @Autowired
    HttpSession httpSession;
    @Autowired
    UserService userService;
    /**
     * 그룹 저장
     * ADMIN 만 가능
     */
//    @PostMapping("/new")
//    public ResponseEntity<?> saveGroup(@RequestBody GroupRequest groupRequest) {
//        log.info("Group controller: /api/group/new ---------------------");
//        try {
//            SessionUser user = (SessionUser) httpSession.getAttribute("user");
//            User user_info= userService.findUserByName(user.getName());
//            if(!user_info.getRole().equals(Role.ADMIN)) //only Admin can manage this function
//                return new ResponseEntity<>("Only admin can create category", HttpStatus.BAD_REQUEST);
//
//            Group group = groupService.saveGroup(groupRequest);
//            log.info("Group controller: /api/group/new ---------------------"+ group.toString());
//            List<Contents> contentsList = group.getContents();
//            List<GroupContentsDTO> groupContentsDTOList = new ArrayList<>();
//            for(int i=0; i<contentsList.size(); i++) {
//                Contents contents = contentsList.get(i);
//                //Content 설정
//                groupContentsDTOList.add(new GroupContentsDTO(contents));
//                log.info("Group controller: /api/group/new ---------------------"+ groupContentsDTOList.size());
//            }
//           // return ResponseEntity.ok(new GroupDTO(group, groupContentsDTOList));
//        }
//        catch (IllegalStateException e) {
//            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
//            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
//        }
//        catch (Exception e){
//            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
//            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//        }
//
//    }

    /**
     * 전체 그룹 검색
     */
//    @GetMapping("")
//    public ResponseEntity<?> sendAllGroupList() {
//        try {
//            log.info("Group controller: /api/group ---------------------");
//            List<Group> groupList = groupService.findAllGroupList();
//            System.out.println("sizes:"+ groupList.size());
//            List<AllGroupDTO> allGroupDTOList = new ArrayList<>();
//            for (int i = 0; i < groupList.size(); i++) {
//                Group group = groupList.get(i);
//                List<Contents> contentsList = group.getContents();
//                List<GroupContentsDTO> groupContentsDTOList = new ArrayList<>();
//                for (int j = 0; j < contentsList.size(); j++) {
//                    //Content 설정
//                    Contents contents = contentsList.get(j);
//                    GroupContentsDTO groupContentsDTO = new GroupContentsDTO(contents);
//                    groupContentsDTOList.add(groupContentsDTO);
//                }
//                // Group 설정
//                AllGroupDTO allGroupDTO = new AllGroupDTO(group, groupContentsDTOList);
//                allGroupDTOList.add(allGroupDTO);
//            }
//            return ResponseEntity.ok(allGroupDTOList);
//        }
//        catch (Exception e){
//            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
//            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//        }
//    }

    /**
     * 그룹 생성
     */
    @PostMapping("/new")
    public ResponseEntity<?> saveGroup(@RequestBody GroupDTO groupDTO) {

        log.info("Group controller: /api/group/new ---------------------");
        try {
            SessionUser user = (SessionUser) httpSession.getAttribute("user");
            User user_info = userService.findUserByName(user.getName());
            if (!user_info.getRole().equals(Role.ADMIN)) //only Admin can manage this function
                return new ResponseEntity<>("Only admin can create category", HttpStatus.BAD_REQUEST);

            Group group = groupService.saveGroup(groupDTO);
            //     log.info("Group controller: /api/group/new ---------------------"+ group.toString());

            return new ResponseEntity<>(new GroupDTO(group), HttpStatus.OK);
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
            List<GroupDTO> groupDTOList= groupService.findAllGroupList()
                    .stream()
                    .filter(group->group!=null)
                    .map(GroupDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(groupDTOList);
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 그룹 하나 검색
     */
    @GetMapping("/{group_id}")
    public ResponseEntity<?> sendGroup(@PathVariable("group_id") long group_id) {
        try {
            log.info("Group controller: /api/group/{group_id} ---------------------");
            Group group = groupService.findGroupById(group_id);
            return ResponseEntity.ok(new GroupDTO(group));
        }
        catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 그룹 수정
     */
    @PutMapping("/edit")
    public ResponseEntity<?> editGroup(@RequestBody GroupDTO groupDTO) {
        try {
            SessionUser user = (SessionUser) httpSession.getAttribute("user");
            User user_info = userService.findUserByName(user.getName());
            if (!user_info.getRole().equals(Role.ADMIN)) //only Admin can manage this function
                return new ResponseEntity<>("Only admin can create category", HttpStatus.BAD_REQUEST);

            Group group = groupService.editGroup(groupDTO);
            return new ResponseEntity<>(new GroupDTO(group), HttpStatus.OK);
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
     * 그룹 삭제
     */
    @DeleteMapping("/delete/{group_id}")
    public ResponseEntity<?> deleteGroup(@PathVariable("group_id") long group_id) {
        try {
            SessionUser user = (SessionUser) httpSession.getAttribute("user");
            User user_info = userService.findUserByName(user.getName());
            if (!user_info.getRole().equals(Role.ADMIN)) //only Admin can manage this function
                return new ResponseEntity<>("Only admin can create category", HttpStatus.BAD_REQUEST);

            Long id = groupService.deleteGroup(group_id);
            return new ResponseEntity<>(id+"is deleted ", HttpStatus.OK);
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



}