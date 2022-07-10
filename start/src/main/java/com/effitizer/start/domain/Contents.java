package com.effitizer.start.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @OneToMany(mappedBy = "contents")
    public List<ContentsGroups> contents_groups = new ArrayList<>(); //책 id


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public String title; // 제목
    public String content; // 내용

    @ColumnDefault("0")
    private boolean isDeleted;//삭제여부

    @OneToMany(mappedBy = "contents", cascade = CascadeType.ALL)
    private List<Contentsfile> contentsfiles = new ArrayList<>();

    @OneToMany(mappedBy = "contents")
    private List<View> views = new ArrayList<>();

    @Builder
    public Contents(User user, Book book, String title, String content) {
        this.title = title;
        this.content = content;
        this.setBook(book);
        this.setUser(user);
    }

    //==연관관계 메서드==//
    public void serBook(Book book) {
        this.book = book;
        book.getContents().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getContents().add(this);
    }

}
