package com.effitizer.start.service;

import com.effitizer.start.domain.Group;
import com.effitizer.start.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupService {
    @Autowired GroupRepository groupRepository;

    public List<Group> findAllGroupList() {
        return groupRepository.findAll();
    }
}
