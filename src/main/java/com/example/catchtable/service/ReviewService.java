package com.example.catchtable.service;

//import com.example.catchtable.dto.review.ReviewRequestDto;
//import com.example.catchtable.model.Review;
//import com.example.catchtable.model.Store;
//import com.example.catchtable.model.User;
//import com.example.catchtable.repository.ReviewRepository;
//import com.example.catchtable.repository.StoreRepository;
//import com.example.catchtable.repository.UserRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

//    private final StoreRepository storeRepository;
//    private final UserRepository userRepository;
//    private final ReviewRepository reviewRepository;
//    @Transactional
//    public ResponseEntity<?> registerReview(Long storeId, String userId, ReviewRequestDto reviewRequestDto) {
//        Store store = storeRepository.findById(storeId).orElseThrow(
//                () -> new NullPointerException("존재하지 않는 가게입니다."));
//        User user = userRepository.findById(userId).orElseThrow(
//                () -> new NullPointerException("존재하지 않는 회원입니다."));
//        Review review = reviewRequestDto.toEntity();
//        Review saveReview = review.createReview(review, user, store);
//
//        // 리뷰 저장 및 리턴
//        return new ResponseEntity<>(reviewRepository.save(saveReview), HttpStatus.valueOf(200));
//    }
}
