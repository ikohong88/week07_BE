package com.example.catchtable.service;

import com.example.catchtable.dto.RestApi;
import com.example.catchtable.dto.reservation.ReservationRequestDto;
import com.example.catchtable.model.Reservation;
import com.example.catchtable.model.Store;
import com.example.catchtable.repository.ReservationRepository;
import com.example.catchtable.repository.StoreRepository;
import com.example.catchtable.dto.review.ReviewResponseDto;
import com.example.catchtable.model.Review;
import com.example.catchtable.model.User;
import com.example.catchtable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {
    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;
    
    private final UserRepository userRepository;

    // 예약하기
    @Transactional
    public RestApi createReservation(ReservationRequestDto requestDto, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new IllegalArgumentException("점포를 찾을수가 없습니다.")
        );
        Reservation reservation = new Reservation(requestDto);
        store.addReservation(reservation);
        reservationRepository.save(reservation);

        String Message = "예약되었습니다.";
        HttpStatus httpStatus = HttpStatus.OK;
        return new RestApi(Message, httpStatus);
    }

    // 예약 취소
    @Transactional
    public RestApi deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("예약을 찾을 수 없습니다.")
        );
        reservationRepository.deleteById(id);

        String Message = "예약이 삭제되었습니다.";
        HttpStatus httpStatus = HttpStatus.OK;
        return new RestApi(Message, httpStatus);
    }

    /**
     * 사용자 예약 목록
     */
    public List<ReservationResponseDto> getAllReservations(String userId) {

        // 사용자 확인
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 회원입니다."));

        List<ReservationResponseDto> reservationResponseDtos = new ArrayList<>();

        // 사용자 예약 목록 가져오기
        List<Reservation> myReservations = reservationRepository.findByUserOrderByCreatedAtDesc(user);

        for (Reservation myReservation : myReservations) {
            ReservationResponseDto reviewResponseDto = new ReservationResponseDto(myReservation);
            reservationResponseDtos.add(reviewResponseDto);
        }
        return reservationResponseDtos;
    }

    /**
     *  (사용자) 예약 상세보기
     */
//    public ReservationResponseDto getReservationDtl(Long storeId, String userId) {
//
//        // 사용자 확인
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new NullPointerException("존재하지 않는 회원입니다."));
//        // 예약 확인
////        Reservation reservation = reservationRepository.findByUser(user).orElseThrow(
////                () -> new NullPointerException("존재하지 않는 회원입니다."));
//
//        // 가게 확인
//        Store store = storeRepository.findById(storeId).orElseThrow(
//                () -> new NullPointerException("존재하지 않는 가게입니다."));
//
//        if(userId == store.ge)
//
//        return new ReservationResponseDto(reservation);
//    }
}
