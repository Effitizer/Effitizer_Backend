package com.effitizer.start.service;

import com.effitizer.start.domain.Role;
import com.effitizer.start.domain.User;
import com.effitizer.start.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUserById(Long user_id) {
        return userRepository.findById(user_id)
                .orElse(null);
    }


    public User findUserByName(String name) {
        return userRepository.findByName(name)
                .orElse(null);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * 사용자의 ROLE를 변경
     * @param newRole 변경될 ROLE
     */
    public User changeRole(String userEmail, String newRole) {
        User user = findUserByEmail(userEmail);
        if (newRole.equals("admin")) {
            user.setRole(Role.ADMIN);
        } else if (newRole.equals("writer")) {
            user.setRole(Role.WRITER);
        }
       return user;
    }

    public void deleteUser(String email){
        User user = findUserByEmail(email);
        userRepository.delete(user);
    }
}
