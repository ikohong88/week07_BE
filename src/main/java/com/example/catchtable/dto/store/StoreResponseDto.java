package com.example.catchtable.dto.store;

import com.example.catchtable.dto.reservation.ReservationResponseDto;
import com.example.catchtable.dto.review.ReviewResponseDto;
import com.example.catchtable.model.Store;
import com.example.catchtable.model.StoreReviewInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponseDto {

    private Long id;
    private String storename;
    private String StoreImageUrl; // 수정 필요? - imageResponseDto를 만들어 List로 만들어야되나? >> private List<imageResponseDto> StoreImageUrl;
    private Float reviewAvg;
    private String category;
    private String region;
    private String launchPrice;
    private String dinnerPrice;
    private Integer phone;
    private String description;
    private Float lat; // 위도
    private Float lng; // 경도 - long은 예약어라 사용 불가능?

    private List<ReviewResponseDto> reviews;
    private List<ReservationResponseDto> reservations;

    public StoreResponseDto(Store store, StoreReviewInfo storeReviewInfo,
                            List<ReviewResponseDto> reviews,
                            List<ReservationResponseDto> reservations) {
        this.id = id;
        this.storename = store.getStorename();
        this.StoreImageUrl = "";
        this.reviewAvg = storeReviewInfo.getAverageReviewScore();
        this.category = store.getCategory();
        this.region = store.getRegion();
        this.launchPrice = store.getLaunchPrice();
        this.dinnerPrice = store.getDinnerPrice();
        this.phone = store.getPhone();
        this.description = store.getDescription();
//        this.lat = store.getlat(); // 카카오 지도 API 확인후 결정
//        this.lng = store.getlng(); // 카카오 지도 API 확인후 결정
        this.reviews = reviews;
        this.reservations = reservations;
    }
}
