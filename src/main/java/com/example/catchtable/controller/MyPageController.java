package com.example.catchtable.controller;

import com.example.catchtable.dto.image.UploadResponseDto;
import com.example.catchtable.dto.user.MyPageResponseDto;
import com.example.catchtable.dto.user.MyPageUpdateDto;
import com.example.catchtable.security.UserDetailsImpl;
import com.example.catchtable.service.S3Service;
import com.example.catchtable.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;
    private final S3Service s3Service;

    // 마이페이지  --> OK
    @GetMapping("/api/users")
    public MyPageResponseDto getMyPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String userId = userDetails.getUser().getId();
        return userService.getMyPage(userId);
    }

    // 유저정보 수정 --> OK --> validation 설정해야함
    @PatchMapping("/api/users")
    public MyPageResponseDto updateMyPage(@RequestBody MyPageUpdateDto myPageUpdateDto,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String userId = userDetails.getUser().getId();
        return userService.updateMyPage(myPageUpdateDto, userId);
    }


    // 이미지 업로드
    @PostMapping("/api/upload")
    public List<UploadResponseDto> uploadImage(@RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {
        return s3Service.uploadImage(files);
    }

    // 이미지 삭제
    @DeleteMapping("api/images")
    public List<String> deleteImages(@RequestBody List<String> filenames){
        return s3Service.deleteImages(filenames);
    }
}
