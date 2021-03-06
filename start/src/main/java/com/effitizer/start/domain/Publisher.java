package com.effitizer.start.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Publisher extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "publisher_id")
    private Long id; //  id

    private String name; //출판사 이름

    @OneToMany(mappedBy = "publisher")
    private List<Book> books = new ArrayList<>();

    @ColumnDefault("0")
    private boolean isDeleted;

    public Publisher (String name) {
        this.name = name;
    }

    //==연관관계 메서드==//
    public void setBook(Book book) {
        this.books.add(book);
        book.setPublisher(this);
    }
}
