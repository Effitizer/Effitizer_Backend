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
@NoArgsConstructor
public class Book extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long id; //  id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher; //출판사 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Writer writer; //작가 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category; //카테고리 id

    private String title; //제목
    private String isbn;

    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private Bookcoverfile bookcoverfile; // 책커버

    public Book(Publisher publisher, Writer writer, Category category, String title, String isbn) {
        this.publisher = publisher;
        this.writer = writer;
        this.category = category;
        this.title = title;
        this.isbn = isbn;
    }
}
