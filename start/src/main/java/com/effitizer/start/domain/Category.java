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
public class Category extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id; //  id
    private String name; // name

    @OneToMany(mappedBy = "category")
    private List<Book> books = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    //==연관관계 메서드==//
    public void setBook(Book book) {
        this.books.add(book);
        book.setCategory(this);
    }
}
