package com.effitizer.start.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class User extends BaseTimeEntity{
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id; //  id

    @Column(nullable = false)
    private String name; //이름

    @Column(nullable = false)
    private String email; //이메일

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Boolean is_subscribed = false; //구독여부

    @OneToOne(mappedBy = "user")
    private Subscribe subscribe;

    @OneToMany(mappedBy = "user")
    private List<User_history> user_historys = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Contents> contents = new ArrayList<>();

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

    //==연관관계 메서드==//
    public void setContents(Contents contents) {
        this.contents.add(contents);
        contents.setUser(this);
    }

    public void setUser_history(User_history user_history) {
        this.user_historys.add(user_history);
        user_history.setUser(this);
    }

}
