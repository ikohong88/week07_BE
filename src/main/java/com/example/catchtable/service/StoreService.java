package com.example.catchtable.service;


import com.example.catchtable.dto.RestApi;
import com.example.catchtable.dto.StoreImageDto;
import com.example.catchtable.dto.reservation.ReservationResponseDto;
import com.example.catchtable.dto.store.StoreResponseDto;
import com.example.catchtable.model.Reservation;
import com.example.catchtable.model.Store;
import com.example.catchtable.model.StoreImageURL;
import com.example.catchtable.repository.StoreImageRepository;
import com.example.catchtable.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreImageRepository storeImageRepository;


    public List<StoreResponseDto> getStores(String filter,String sort,String word,String minPrice,String maxPrice) {
        // .equal로 사용시 에러 발생
        if (filter == "") {
            filter = null;
        }
        if (sort == "" ) {
            sort = null;
        }
        if (word == "") {
            word = null;
        }
        if (minPrice == "") {
            minPrice = null;
        }
        if (maxPrice == "") {
            maxPrice = null;
        }

        List<Store> stores = new ArrayList<>();
        if (filter == null && sort == null && word == null && minPrice == null && maxPrice == null) {
            stores = storeRepository.findAllByOrderByIdDesc();
        } 
        // 가게 이름 검색 기능 - 카테고리로 해야되는건가??
        else if (word != null) {
            stores = storeRepository.findByStorenameIsContaining(word);
        }
        List<StoreResponseDto> result = new ArrayList<>();
        for(Store store : stores) {
            StoreResponseDto responseDto = new StoreResponseDto(store);
            result.add(responseDto);
        }
        return result;
    }

    // 가게 상세 장보 조회
    public StoreResponseDto getStore(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException("해당 가게가 존재하지 않습니다.")
        );
        List<Reservation> reservations = store.getReservations();  // 연관관계 매핑으로 연결된 데이터를 조회할 수 있다.
        List<ReservationResponseDto> reservationResponseDtos = new ArrayList<>();
        if(reservations.size() > 0) {
            for(Reservation reservation : reservations) {
                ReservationResponseDto reservationResponseDto = new ReservationResponseDto(reservation);
                reservationResponseDtos.add(reservationResponseDto);
            }
        }
        List<StoreImageURL> storeImageURLS = store.getStoreImageURLS();
        List<StoreImageDto> storeImageDtos = new ArrayList<>();
        if(storeImageURLS.size() > 0) {
            for(StoreImageURL storeImageURL : storeImageURLS) {
                StoreImageDto storeImageDto = new StoreImageDto(storeImageURL);
                storeImageDtos.add(storeImageDto);
            }
        }
        return new StoreResponseDto(store, reservationResponseDtos, storeImageDtos);
    }

    // 가게 이미지 등록
    @Transactional
    public RestApi createStoreImageURL(StoreImageDto storeImageDto, Long id) {
        Store store  = storeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("점포를 찾을수가 없습니다.")
        );
        StoreImageURL storeImageURL = new StoreImageURL(storeImageDto);
        store.addStoreImageURL(storeImageURL);
        storeImageRepository.save(storeImageURL);

        String Message = "이미지 등록이 완료되었습니다.";
        HttpStatus httpStatus = HttpStatus.OK;
        return new RestApi(Message, httpStatus);
    }
}
