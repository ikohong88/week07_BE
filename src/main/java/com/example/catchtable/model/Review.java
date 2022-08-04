package com.example.catchtable.model;

import com.example.catchtable.dto.review.ReviewRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
public class Review extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private Long id;

    @Column(nullable = false)
    private String title;
    private String content;
    private Float rate;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "review")
    private List<Image> images;

//    private List<String> images; // String 으로 바꿀때

    //== 연관관계 (편의) 메서드==// 양방향 연관관계 세팅을 까먹지않고 할수있는 장점

    public void setUser(User user) {
        this.user = user;
        user.getReviews().add(this);
    }
    public void setStore(Store store) {
        this.store = store;
        store.getReviews().add(this);
    }
    public void updateStore(Store store) {
        this.store = store;
        // 무한 루프에 빠지지 않기 위해 작성
        if(!store.getReviews().contains(this))
            store.addReview(this);
    }

    public Review(ReviewRequestDto reviewRequestDto, User user, Store store) {
        this.title = reviewRequestDto.getTitle();
        this.content = reviewRequestDto.getContent();
        this.rate = reviewRequestDto.getRate();
        this.images = reviewRequestDto.getImages().stream()
                .map((image) -> new Image(image, this))
                .collect(Collectors.toList());
//        this.images = reviewRequestDto.getImages();
        setUser(user);
        setStore(store);
    }

    @Builder
    public Review(String title, String content, Float rate, List<Image> images, User user, Store store) {
        this.title = title;
        this.content = content;
        this.rate = rate;
        this.images = images;
        setUser(user);
        setStore(store);
    }

    // static 붙이면 this error
    public Review createReview(ReviewRequestDto reviewRequestDto, User user, Store store) {
        return Review.builder()
                .title(reviewRequestDto.getTitle())
                .content(reviewRequestDto.getContent())
                .rate(reviewRequestDto.getRate())
                .images(reviewRequestDto.getImages().stream()
                        .map((image) -> new Image(image, this))
                        .collect(Collectors.toList()))
                .user(user)
                .store(store)
                .build();
    }
}
