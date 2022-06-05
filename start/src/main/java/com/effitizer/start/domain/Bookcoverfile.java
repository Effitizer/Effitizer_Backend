package com.effitizer.start.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bookcoverfile extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "bookcoverfile_id")
    private Long id; //  id

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book; //컨텐츠 id

    private String real_name; // 실파일명
    private String name; // 파일명
    private Long size; // 파일크기
    private String path; // 파일경로
    private String extend; // 파일확장자

    @Builder
    public Bookcoverfile(Book book, String real_name, String name, Long size, String path, String extend) {
        this.real_name = real_name;
        this.name = name;
        this.size = size;
        this.path = path;
        this.extend = extend;

        this.setBook(book);

    }

    //==연관관계 메서드==//
    public void setBook(Book book) {
        this.book = book;
        book.setBookcoverfile(this);
    }
}
