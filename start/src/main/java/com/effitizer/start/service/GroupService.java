package com.effitizer.start.service;

import com.effitizer.start.domain.Contents;
import com.effitizer.start.domain.Group;
import com.effitizer.start.domain.Publisher;
import com.effitizer.start.domain.dto.Group.GroupDTO;
import com.effitizer.start.domain.dto.Group.Request.GroupContentsRequest;
import com.effitizer.start.domain.dto.Group.Request.GroupRequest;
import com.effitizer.start.repository.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class GroupService {
    @Autowired GroupRepository groupRepository;

    /**
     * 그룹 저장
     */
//    public Group saveGroup(GroupRequest groupRequest) {
//        List<Contents> contentsList = new ArrayList<>();
//        List<GroupContentsRequest> groupContentsRequestList = groupRequest.getContents();
//        // contents 찾기
//        for(int i=0; i<groupContentsRequestList.size(); i++) {
//            GroupContentsRequest groupContentsRequest = groupContentsRequestList.get(i);
//            Contents contents = contentsService.findContensById(groupContentsRequest.getId());
//
//            if(!contents.getTitle().equals(groupContentsRequest.getTitle())) { // title이 동일하지 않을 때
//                log.info("GroupService: 데이터 정보 오류");
//                throw new IllegalStateException("데이터 정보가 올바르지 않습니다.");
//            }
//            contentsList.add(contents);
//        }
//        Group group = new Group(groupRequest.getTitle(), contentsList);
//        return groupRepository.save(group);
//    }

    /**
     * 그룹 전체 조회
     */
    public List<Group> findAllGroupList() {
        return groupRepository.findAll();
    }

    public Group saveGroup(GroupDTO groupDTO) {
        Group group = new Group(groupDTO.getTitle());
        return groupRepository.save(group);
    }

    public Group findGroupById(Long groupId){
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalStateException("그룹 정보가 올바르지 않습니다"));
        return group;
    }


    public Group editGroup(GroupDTO groupDTO) {
        Group group = groupRepository.findById(groupDTO.getId())
                .orElseThrow(() -> new IllegalStateException("그룹 정보가 올바르지 않습니다"));
        group.setTitle(groupDTO.getTitle());

        return group;
    }

    public Long deleteGroup(long group_id){
        Group group = groupRepository.findById(group_id)
                .orElseThrow(() -> new IllegalStateException("그룹 정보가 올바르지 않습니다"));
        groupRepository.delete(group);
        return group_id;
    }
}
