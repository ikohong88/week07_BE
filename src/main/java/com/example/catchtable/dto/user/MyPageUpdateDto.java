package com.example.catchtable.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
// 마이페이지 정보 수정 요청 Dto
public class MyPageUpdateDto {

    @NotBlank(message = "이름을 작성해주세요.")
    private String username;         // 사용자 이름
    private String profileUrl;       // 프로필 이미지
    private String description;      // 자기소개
    @Size(max = 15, message = "활동지역은 15글자 이내로 작성 가능합니다.")
    private String region;           // 활동지역
    @Size(max = 11, message = "-없이 숫자로 작성해주세요")
    private Integer phone;           // 01012345678
    private String gender;           // ’F’ | ‘M’
    private Date birth;              // 2003-02-23
//    @Range(min = 1000, max = 400000)
    private Integer priceMin;        // 최소 가격 0원 ~
//    @Range(min = 1000, max = 400000)
    private Integer priceMax;        // 최대 가격       40만원

}
