package com.effitizer.start.domain;

import lombok.Builder;
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
@Table(name = "GroupList")
public class Group extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "group_id")
    private Long id; //  id

    @Column(name="title")
    private String title; // 주제

    @OneToMany(mappedBy = "group")
    public List<ContentsGroups> contents_groups = new ArrayList<>(); //책 id



    @Builder
    public Group(String title){
        this.title=title;
    }
//    public Group(String title, List<Contents> contents) {
//        this.title = title;
//     //   this.contents = contents;
//        this.setContents(contents);
//    }

//    //==연관관계 메서드==//
//    public void setContents(List<Contents> contentsList) {
//        for (int i=0; i<contentsList.size(); i++) {
//            Contents contents = contentsList.get(i);
//            contents.getGroups().add(this);
//        }
//    }
}
