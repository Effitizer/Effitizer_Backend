package com.effitizer.start.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ContentsGroups {

    @Id
    @GeneratedValue
    @Column(name = "contents_groups_id")
    private Long id; //  id

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "contents_id")
    private Contents contents;


    @Builder
    public ContentsGroups(Contents contents, Group group){
        this.contents= contents;
        this.group= group;
    }
}
