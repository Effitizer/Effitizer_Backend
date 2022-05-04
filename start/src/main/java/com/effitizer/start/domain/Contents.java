package com.effitizer.start.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Contents extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long id; //  id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user_id; //회원 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book_id; //책 id

    private Long book_order; // 책순서
    private String title; // 제목
    private String content; // 내용
    private Long view; // 조회수


}
