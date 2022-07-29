package com.example.catchtable.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(nullable = false)
    private Date date;

    private Integer member;

    @ManyToOne
//    @JoinColumn(name = "")
    private User user;
//    private Long userId;

    @ManyToOne
//    @JoinColumn(name = "")
    private Store store;
//    private Long storeId;
}
