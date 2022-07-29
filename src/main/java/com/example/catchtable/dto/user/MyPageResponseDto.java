package com.example.catchtable.dto.user;

import com.example.catchtable.dto.review.ReviewResponseDto;
import com.example.catchtable.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponseDto {

    private String id;
    private String username;
    private String profileUrl;       // 프로필 이미지
    private String description;      // 자기소개
    private String region;           // 활동지역;
    private Integer phone;           // 01012345678
    private String gender;           // ’F’ | ‘M’,
    private Date birth;              // 2003-02-23,
    private Integer priceMin;        // 최소 가격
    private Integer priceMax;        // 최대 가격

    // 리뷰
    private List<ReviewResponseDto> reviews;

    public MyPageResponseDto(User user, List<ReviewResponseDto> reviews) {
        this.id = user.getId();
        this.id = user.getUsername();
        this.profileUrl = user.getProfileUrl();
        this.description = user.getDescription();
        this.region = user.getRegion();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.birth = user.getBirth();
        this.priceMin = user.getPriceMin();
        this.priceMax = user.getPriceMax();
        this.reviews = reviews;
    }


}



