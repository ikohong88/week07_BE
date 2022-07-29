package com.example.catchtable.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private Long storeId;
    private String storeName;
    private String title;
    private String content;
    private Float rate;
//    private List<ImageResponseDto> image;
    private Date createdAt;
}
