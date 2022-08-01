package com.example.catchtable.service;

import com.example.catchtable.dto.user.SignUpRequestDto;
import com.example.catchtable.model.User;
import com.example.catchtable.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class UserService{

    public final UserRepository userRepository;
    public final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // 회원가입
    public void signup(SignUpRequestDto requestDto) {
        String id = requestDto.getId();
        String pw = passwordEncoder.encode(requestDto.getPw());
        String username = requestDto.getUsername();

        User user =new User(id, pw, username);
        if (userRepository.findById(id).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 아이디 입니다.");
        }
        userRepository.save(user);

    }


    // ID 중복확인


    // 로그인



    // 회원 탈퇴



}
