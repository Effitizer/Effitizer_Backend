package com.effitizer.start.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder
    public Contentsfile(Contents contents, String real_name, String name, Long size, String path, String extend) {
        this.real_name = real_name;
        this.name = name;
        this.size = size;
        this.path = path;
        this.extend = extend;

        this.setContents(contents);
    }

    //==연관관계 메서드==//
    public void setContents(Contents contents) {
        this.contents = contents;
        contents.getContentsfiles().add(this);
    }

}


