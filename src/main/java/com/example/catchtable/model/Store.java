package com.example.catchtable.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(nullable = false, unique = true)
    private String storename;
    private String storeImageUrl;
    private String category;
    private String region;
    private String launchPrice;
    private String dinnerPrice;
    private Integer phone;
    private String description;

    @OneToOne(mappedBy = "store")
    private StoreReviewInfo storeReviewInfo;
}