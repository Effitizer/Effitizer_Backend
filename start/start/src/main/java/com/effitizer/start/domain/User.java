package com.effitizer.start.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class User {
    @Id @GeneratedValue
    private Long id; //  id

    @Column(nullable = false)
    private String name; //이름

    @Column(nullable = false)
    private String nicekname; //닉이름

    @Column(nullable = false)
    private String email; //이메일

    @Column(nullable = false)
    private String password; //비밀번호

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "provider_type")
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column(nullable = false)
    private Boolean is_subscribed; //구독여부

    private String gender; //연령대
    private String age_range; //역할
    private LocalDateTime create_time; //생성일
    private LocalDateTime update_time; //수정일





}
