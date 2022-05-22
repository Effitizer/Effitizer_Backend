package com.effitizer.start.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Contentsfile extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "contentsfile_id")
    private Long id; //  id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_id")
    private Contents contents; //컨텐츠 id

    private String real_name; // 실파일명
    private String name; // 파일명
    private Long size; // 파일크기
    private String path; // 파일경로
    private String extend; // 파일확장자

    public Contentsfile(Contents contents, String real_name, String name) {
        this.contents = contents;
        this.real_name = real_name;
        this.name = name;
    }

}


