package com.effitizer.start.service;

import com.effitizer.start.domain.Group;
import com.effitizer.start.domain.Publisher;
import com.effitizer.start.domain.dto.Group.GroupDTO;
import com.effitizer.start.repository.GroupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GroupServiceTest {
    @Autowired
    GroupRepository groupRepository;
    @Autowired GroupService groupService;

    @Test
    public void 그룹수정저장확인() throws Exception{
        // Given
        Group group1 = createGroup("group1");
        String title = "group2";
        GroupDTO groupDTO = new GroupDTO(group1.getId(), title);

        // When
        groupService.editGroup(groupDTO);

        // Then
        assertEquals("그룹 데이터 수정 확인", group1.getTitle(), title);
    }

    private Group createGroup(String name) {
        Group group = new Group(name);
        groupRepository.save(group);
        return group;

    }



}
