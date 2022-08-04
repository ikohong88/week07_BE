package com.example.catchtable.dto.review;

import com.example.catchtable.dto.image.ImageRequestDto;
import com.example.catchtable.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ReviewRequestDto {

   @NotBlank(message = "제목을 입력해주세요")
   @Size(min = 1, max = 20, message = "1 ~ 20글자 이내로 작성해주세요")
   private String title;   // 제목
   @NotBlank(message = "내용을 입력해주세요")
   @Size(min = 10, max = 200, message = "10 ~ 200글자 이내로 작성해주세요")
   private String content; // 내용
   @NotBlank(message = "1~5 사이의 별점을 입력해 주세요")
   private Float rate;     // 평점

   private List<ImageRequestDto> image;

   public Review toEntity() {
      return Review.builder()
              .title(title)
              .content(content)
              .rate(rate)
              .build();
   }
}
