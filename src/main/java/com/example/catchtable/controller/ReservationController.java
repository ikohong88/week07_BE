package com.example.catchtable.controller;

import com.example.catchtable.dto.RestApi;
import com.example.catchtable.dto.reservation.ReservationRequestDto;
import com.example.catchtable.service.ReservationService;
import com.example.catchtable.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final StoreService storeService;

    // 예약 하기
    @PostMapping("/api/reservation/store/{storeId}")
    public RestApi postReservation(@RequestBody ReservationRequestDto requestDto, @PathVariable Long storeId) {
        return reservationService.createReservation(requestDto, storeId);
    }

   //예약 취소
    @DeleteMapping("/api/reservation/{id}")
    public RestApi deleteReservation(@PathVariable Long id) {
         return reservationService.deleteReservation(id);
    }
}