package com.example.catchtable.model;

import com.example.catchtable.dto.image.ImageRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    private String url;         // 이미지 URL

    private String filename;    // 저장된 파일명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    public Image(ImageRequestDto imageRequestDto, Review review) {
        this.url = imageRequestDto.getUrl();
        this.filename = imageRequestDto.getFilename();
        this.review = review;
    }

}
