package com.effitizer.start.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class UserHistory extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "user_history_id")
    private Long id; //  id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user; // 회원 기록

    private String accessName; // 접근 이름
    private String accessPath; // 접근 경로
    private String ipAddress; // 아이피 주소
    private String loginDate; // 로그인 날짜
    private String osType; // os 종류
    private String sessionLastaccess; // 세션 마지막 접속
}
