package com.example.catchtable.service;

import com.example.catchtable.dto.RestApi;
import com.example.catchtable.dto.reservation.ReservationRequestDto;
import com.example.catchtable.dto.reservation.ReservationResponseDto;
import com.example.catchtable.model.Reservation;
import com.example.catchtable.model.Store;
import com.example.catchtable.model.User;
import com.example.catchtable.repository.ReservationRepository;
import com.example.catchtable.repository.StoreRepository;
import com.example.catchtable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

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
    public List<ReservationResponseDto> getUserReservations(String userId) {

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
     * 가게 예약 목록
     */
    public List<ReservationResponseDto> getStoreReservations(Long storeId, String id) {

        // 사용자 확인
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 가게입니다."));

        List<ReservationResponseDto> reservationResponseDtos = new ArrayList<>();

        // 사용자 예약 목록 가져오기
        List<Reservation> storeReservations = reservationRepository.findByStoreOrderByCreatedAtDesc(store);

        for (Reservation storeReservation : storeReservations) {
            ReservationResponseDto reviewResponseDto = new ReservationResponseDto(storeReservation);
            reservationResponseDtos.add(reviewResponseDto);
        }
        return reservationResponseDtos;
    }

    /**
     * (사용자) 예약 상세보기
     */
    public ReservationResponseDto getReservationDtl(Long reservationId, String userId) {
        // 예약 확인
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 회원입니다."));
        return new ReservationResponseDto(reservation);
    }

}
