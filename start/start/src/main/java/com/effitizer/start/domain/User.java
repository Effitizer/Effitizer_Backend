package com.effitizer.start.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class User {
    @Id @GeneratedValue
    private Long id; // 회원 id

    private String name; //이름
    private String email; //이메일
    private String password; //비밀번호
    private Boolean is_subscribed; //구독여부
    private String gender; //연령대
    private String age_range; //역할
    private LocalDateTime create_time; //생성일
    private LocalDateTime update_time; //수정일


}
