package com.example.catchtable.dto.review;

import com.example.catchtable.dto.ImageResponseDto;
import com.example.catchtable.model.Image;
import com.example.catchtable.model.Review;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private Long storeId;
    private String storename;
    private String title;
    private String content;
    private Float rate;

    private List<String> images;
    private LocalDateTime createdAt;


    public ReviewResponseDto(Review myReview) {
        this.id = myReview.getId();
        this.storeId = myReview.getStore().getId();
        this.storename = myReview.getStore().getStorename();
        this.title = myReview.getTitle();
        this.content = myReview.getContent();
        this.rate = myReview.getRate();
        this.images = myReview.getImages().stream()
                                        .map(Image::getUrl)
                                        .collect(Collectors.toList());
        this.createdAt = myReview.getCreatedAt();
    }
}
