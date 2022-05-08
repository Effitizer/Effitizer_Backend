package com.effitizer.start.domain;

import com.effitizer.start.domain.dto.ContentsDTO;
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
public class Contents extends BaseTimeEntity{
    @Id
    @GeneratedValue
    public Long id; //  id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user_id; //회원 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    public Book book_id; //책 id

    public String title; // 제목
    public String content; // 내용
    public Long view; // 조회수

    @OneToMany(mappedBy = "contents", cascade = CascadeType.ALL)
    public List<Contentsfile> contentsfiles = new ArrayList<>();

    @Builder(builderMethodName = "createBuilder", builderClassName = "createBuilder")
    public Contents(ContentsDTO contentsDTO) {
        this.id = contentsDTO.id;
        this.user_id = contentsDTO.user_id;
        this.title = contentsDTO.title;
        this.content = contentsDTO.content;
    }

}
