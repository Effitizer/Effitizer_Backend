package com.effitizer.start.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class View extends BaseTimeEntity{
    @Id
    @GeneratedValue
    @Column(name = "view_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_id")
    public Contents contents; //책 id

    @Builder
    public View (Long id, Contents contents) {
        this.id = id;
        this.setContents(contents);
    }

    public void setContents(Contents contents) {
        this.contents = contents;
        contents.getViews().add(this);
    }
}
