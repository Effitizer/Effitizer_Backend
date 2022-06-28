package com.effitizer.start.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "contents_groups")
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

}
