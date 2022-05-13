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
public class Group extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long id; //  id

    private String title; // 주제

    @OneToMany(mappedBy = "group")
    private List<Contents> contents = new ArrayList<>();

    //==연관관계 메서드==//
    public void setContents(Contents contents) {
        this.contents.add(contents);
        contents.setGroup(this);
    }
}
