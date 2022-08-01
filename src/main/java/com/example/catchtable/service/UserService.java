package com.example.catchtable.service;

import com.example.catchtable.dto.review.ReviewResponseDto;
import com.example.catchtable.dto.user.MyPageResponseDto;
import com.example.catchtable.dto.user.MyPageUpdateDto;
import com.example.catchtable.model.Review;
import com.example.catchtable.model.User;
import com.example.catchtable.repository.ReviewRepository;
import com.example.catchtable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    /**
     * 마이 페이지
     */
    public MyPageResponseDto getMyPage(String userId) {

        // 사용자 확인
        User foundUer = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 사용자입니다"));

        return new MyPageResponseDto(foundUer);
    }


    /**
     * 사용자 정보 수정
     */
    @Transactional
    public MyPageResponseDto updateMyPage(MyPageUpdateDto myPageUpdateDto, String userId) {

        // 사용자 확인
        User foundUer = userRepository.findById(userId).orElseThrow(
                () -> new NullPointerException("존재하지 않는 사용자입니다"));
        // 사용자 데이터 정보 업데이트
        foundUer.updateUser(myPageUpdateDto);

        return new MyPageResponseDto(myPageUpdateDto, foundUer);
    }
}
