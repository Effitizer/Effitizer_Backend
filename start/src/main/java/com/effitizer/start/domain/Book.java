package com.effitizer.start.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long id; //  id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher; //출판사 id

    @ManyToOne()
    @JoinColumn(name = "writer_id")
    private User writer; //작가 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category; //카테고리 id

    private String title; //제목
    private String isbn;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private Bookcoverfile bookcoverfile; // 책커버

    @Builder
    public Book(Publisher publisher, User user, Category category, String title, String isbn) {
        this.title = title;
        this.isbn = isbn;

        this.setPublisher(publisher);
        this.setWriter(user);
        this.setCategory(category);

    }

    //==연관관계 메서드==//
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
        publisher.getBooks().add(this);
    }

    public void setWriter(User user) {
        this.writer = user;
        user.getBooks().add(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getBooks().add(this);
    }
}
