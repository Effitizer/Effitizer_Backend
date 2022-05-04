package com.effitizer.start.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Writer extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long id; //  id

    private String name // 작가 이름
}
