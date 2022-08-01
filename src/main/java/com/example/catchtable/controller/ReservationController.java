package com.example.catchtable.controller;

import com.example.catchtable.dto.reservation.ReservationResponseDto;
import com.example.catchtable.security.UserDetailsImpl;
import com.example.catchtable.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

//    // 예약 하기
//    @PostMapping(/api/reservation/store/{storeId})
//
//
//
//   //예약 취소
//    @DeleteMapping(/api/reservation/{id})
//

    // 사용자 예약 목록 불러오기
    @GetMapping("/api/users/reservations")
    public List<ReservationResponseDto> getAllReservations(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return reservationService.getAllReservations(userDetails.getUser().getId());
    }

    // 사용자 예약 상세 보기
//    @GetMapping("/api/users/reservations/{storeId}")
//    public ReservationResponseDto getReservationDtl(@PathVariable Long storeId,
//                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        String userId = userDetails.getUser().getId();
//        return reservationService.getReservationDtl(storeId, userId);
//    }

}

