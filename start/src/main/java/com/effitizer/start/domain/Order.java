package com.effitizer.start.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Order extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id; //  id

    private Long order_num; // 책 순서

    @OneToMany(mappedBy = "order")
    private Contents contents;
}
