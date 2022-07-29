package com.example.catchtable.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDto {
   private String title;   //제목
   private String content; //내용
   // private List<ImageResponseDto> image;


}
