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
    private Long id; //  id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_id")
    private Contents contents_id; //컨텐츠 id

    private String name; // 파일명
    private String real_name; // 실파일명
    private Long size; // 파일크기
    private String path; // 파일경로
    private String extend; // 파일확장자

}


