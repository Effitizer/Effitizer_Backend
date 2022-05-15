package com.effitizer.start.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id; //  id

    private int order_num; // 책 순서

    @OneToOne(mappedBy = "order")
    private Contents contents;

    public Order(int order_num, Contents contents) {
        this.order_num = order_num;
        this.contents = contents;
        setConsumer(contents);
    }

    //==연관관계 메서드==//
    public void setConsumer(Contents contents) {
        this.contents = contents;
        contents.setOrder(this);
    }
}
