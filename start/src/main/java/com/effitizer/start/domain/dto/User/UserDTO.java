package com.effitizer.start.domain.dto.User;

import com.effitizer.start.domain.Role;
import com.effitizer.start.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;

    public UserDTO (User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
