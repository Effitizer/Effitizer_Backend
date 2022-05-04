package com.effitizer.start.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class User{
    @Id @GeneratedValue
    private Long id; //  id

    @Column(nullable = false)
    private String name; //이름

    @Column(nullable = false)
    private String email; //이메일

    //@Column(nullable = false)
    //private String nickname; //닉이름

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Boolean is_subscribed = false; //구독여부

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime create_time; //생성일

    @LastModifiedBy
    private LocalDateTime update_time; //수정일

    @Builder
    public User(String name, String email, Role role){
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public User update(String name) {
        this.name = name;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }





}
