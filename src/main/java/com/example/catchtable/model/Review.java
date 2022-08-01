package com.example.catchtable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@AllArgsConstructor
public class Review extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String content;
    private Float rate;

    @ManyToOne(fetch = LAZY)
    private User user;

    @ManyToOne(fetch = LAZY)
    private Store store;

    //== 연관관계 (편의) 메서드==// 양방향 연관관계 세팅을 까먹지않고 할수있는 장점
    public void setUser(User user) {
        this.user = user;
        user.getReviews().add(this);
    }

    public void setStore(Store store) {
        this.store = store;
        store.getReviews().add(this);
    }

    @Builder
    public Review(String title, String content, Float rate, User user, Store store) {
        this.title = title;
        this.content = content;
        this.rate = rate;
        setUser(user);
        setStore(store);
    }

    public Review createReview(Review review, User user, Store store) {
        return Review.builder()
                .title(review.getTitle())
                .content(review.getContent())
                .rate(review.getRate())
                .user(user)
                .store(store)
                .build();
    }
}
