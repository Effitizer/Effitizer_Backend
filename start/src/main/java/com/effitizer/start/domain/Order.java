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
@Table(name = "BookOrder")
public class Order extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id; //  id

    private int orderNum; // 책 순서

    @OneToOne(mappedBy = "order")
    private Contents contents;

    public Order(int orderNum, Contents contents) {
        this.orderNum = orderNum;

        setConsumer(contents);
    }

    //==연관관계 메서드==//
    public void setConsumer(Contents contents) {
        this.contents = contents;
        contents.setOrder(this);
    }
}
