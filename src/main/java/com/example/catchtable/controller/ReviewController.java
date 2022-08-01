package com.example.catchtable.controller;

import com.example.catchtable.dto.review.ReviewRequestDto;
import com.example.catchtable.dto.review.ReviewResponseDto;
import com.example.catchtable.security.UserDetailsImpl;
import com.example.catchtable.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/api/review/stores/{storeId}")
    public ResponseEntity<?> registerReview(@PathVariable Long storeId,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails,
                                            @RequestBody ReviewRequestDto reviewRequestDto) {
        if (userDetails != null) {
            String userId = userDetails.getUser().getId();
            return reviewService.registerReview(storeId, userId, reviewRequestDto);
        }
        return new ResponseEntity<>("로그인 후 사용해주세요", HttpStatus.valueOf(401));
    }

    // (사용자) 리뷰 목록
    @GetMapping("/api/users/reivews")
    public List<ReviewResponseDto> getUserReviews(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return reviewService.getUserReviews(userDetails.getUser().getId());
    }

    // (가게) 리뷰 목록
    @GetMapping("/api/reivews/stores/{id}")
    public List<ReviewResponseDto> getStoreReviews(@PathVariable Long id) {
        return reviewService.getStoreReviews(id);
    }

}
