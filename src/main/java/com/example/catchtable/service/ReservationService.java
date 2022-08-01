package com.example.catchtable.service;

import com.example.catchtable.dto.RestApi;
import com.example.catchtable.dto.reservation.ReservationRequestDto;
import com.example.catchtable.model.Reservation;
import com.example.catchtable.model.Store;
import com.example.catchtable.repository.ReservationRepository;
import com.example.catchtable.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final StoreRepository storeRepository;
    private final ReservationRepository reservationRepository;

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
}
