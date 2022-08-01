package com.example.catchtable.dto.store;

import com.example.catchtable.dto.StoreImageDto;
import com.example.catchtable.dto.reservation.ReservationResponseDto;
import com.example.catchtable.model.Store;
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
    private Float reviewAvg;
    private String category;
    private String region;
    private String launchPrice;
    private String dinnerPrice;
    private Integer phone;
    private String description;
    private Float lat; // 위도
    private Float lng; // 경도 - long은 예약어라 사용 불가능?

//    private List<ReviewResponseDto> reviews;
    
    // 예약 리스트
    private List<ReservationResponseDto> reservations;
    // 가게 이미지 리스트
    private List<StoreImageDto> storeImages;

    // 가게 전체 조회
    public StoreResponseDto(Store store) {
        this.id = store.getId();
        this.storename = store.getStorename();
        this.reviewAvg = 3.4f;
        this.category = store.getCategory();
        this.region = store.getRegion();
        this.launchPrice = store.getLaunchPrice();
        this.dinnerPrice = store.getDinnerPrice();
        this.phone = store.getPhone();
        this.description = store.getDescription();
    }

    // 가게 상세 정보
    public StoreResponseDto(Store store,
                            List<ReservationResponseDto> reservations,
                            List<StoreImageDto> storeImages) {
        this.storename = store.getStorename();
//        this.reviewAvg = storeReviewInfo.getAverageReviewScore();
        this.category = store.getCategory();
        this.region = store.getRegion();
        this.launchPrice = store.getLaunchPrice();
        this.dinnerPrice = store.getDinnerPrice();
        this.phone = store.getPhone();
        this.description = store.getDescription();
        this.lat = store.getLat(); // 카카오 지도 API 확인후 결정
        this.lng = store.getLng(); // 카카오 지도 API 확인후 결정
//        this.reviews = reviews;
        this.reservations = reservations;
        this.storeImages = storeImages;
    }
}
