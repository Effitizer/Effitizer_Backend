package com.effitizer.start.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Contents extends BaseTimeEntity{
    @Id
    @GeneratedValue
    public Long id; //  id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user; //회원 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    public Book book; //책 id

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public String title; // 제목
    public String content; // 내용
    public Long view; // 조회수

    @OneToMany(mappedBy = "contents", cascade = CascadeType.ALL)
    public List<Contentsfile> contentsfiles = new ArrayList<>();

}
