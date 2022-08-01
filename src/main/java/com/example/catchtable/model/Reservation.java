package com.example.catchtable.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@AllArgsConstructor
public class Reservation extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date date;

    private Integer member;

    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "")
    private User user;
//    private Long userId;

    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "")
    private Store store;
//    private Long storeId;

    // status 값이 있어야 할듯 - 방문예정 / 방문완료 / 취소&노쇼
    // 시간은 어떻게 ? 그냥 예약하면 예약하기로 ?
}
