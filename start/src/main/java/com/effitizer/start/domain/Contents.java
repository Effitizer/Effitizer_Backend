package com.effitizer.start.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contents extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "contents_id")
    public Long id; //  id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user; //회원 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    public Book book; //책 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    public Group group; //책 id

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public String title; // 제목
    public String content; // 내용
    private Long view; // 조회수
    private String isbn; // 국제표준도서번호

    @OneToMany(mappedBy = "contents", cascade = CascadeType.ALL)
    private List<Contentsfile> contentsfiles = new ArrayList<>();

    public Contents(User user, Book book, String title, String content) {
        this.user = user;
        this.book = book;
        this.title = title;
        this.content = content;
    }

    //==연관관계 메서드==//
    public void setContetnsfile(Contentsfile contetnsfile) {
        this.contentsfiles.add(contetnsfile);
        contetnsfile.setContents(this);
    }

    public void setOrder(Order order) {
        this.order = order;
        order.setContents(this);
    }

}
