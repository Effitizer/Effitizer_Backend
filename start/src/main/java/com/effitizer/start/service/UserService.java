package com.effitizer.start.service;

import com.effitizer.start.domain.User;
import com.effitizer.start.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUserById(Long user_id) {
        return userRepository.findById(user_id)
                .orElse(null);
    }

    public User findUserByName(String name){
        return userRepository.findByName(name)
                .orElse(null);
    }
}
