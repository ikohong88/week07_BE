package com.example.catchtable.controller;

import com.example.catchtable.dto.user.SignUpRequestDto;
import com.example.catchtable.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Validated
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 회원가입
    @PostMapping("/api/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid SignUpRequestDto requestDto) {
        userService.signup(requestDto);
        return new ResponseEntity<>("회원가입에 성공하였습니다. ", HttpStatus.valueOf(201));
    }


//    // 회원가입 - ID 중복체크
//    @PostMapping("/api/users/{id}")
//



//    // 로그인
//    @PostMapping("/api/signin")



//    // 회원 탈퇴
//    @DeleteMapping("/api/user")
//


}
