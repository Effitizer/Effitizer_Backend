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
public class Writer extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "writer_id")
    private Long id; //  id

    private String name; // 작가 이름

    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();

    //==연관관계 메서드==//
    public void setBook(Book book) {
        this.books.add(book);
        book.setWriter(this);
    }
}
