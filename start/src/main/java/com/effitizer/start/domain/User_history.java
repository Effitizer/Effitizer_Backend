package com.effitizer.start.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class User_history {
    @Id
    @GeneratedValue
    @Column(name = "user_history_id")
    private Long id; //  id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user; // 회원 기록

    private LocalDateTime created_at; //생성시간
    private LocalDateTime updated_at; // 수정 시간
    private String access_name; // 접근 이름
    private String access_path; // 접근 경로
    private String ip_address; // 아이피 주소
    private LocalDateTime login_date; // 로그인 날짜
    private String os_type; // os 종류
    private String session_lastaccess; // 세션 마지막 접소
}
