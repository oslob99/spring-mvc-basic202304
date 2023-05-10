package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.LoginRequestDTO;
import com.spring.mvc.chap05.dto.SignUpRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService service;

    @Test
    @DisplayName("signUPDTO를 전달하면 회원가입에 성공해야한다")
    void joinTest(){
        SignUpRequestDTO requestDTO = new SignUpRequestDTO();
        requestDTO.setAccount("oslob");
        requestDTO.setPassword("1234");
        requestDTO.setName("고래상어");
        requestDTO.setEmail("Oslob@test.com");

        service.join(requestDTO);
    }

    @Test
    @DisplayName("계정명이 abc1234인 회원의 로그인 시도시 결과 검증을  상황별로 수행해야한다")
    void loginTest(){
        // given
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setAccount("abc1234");
        dto.setPassword("1!");

        // when
        LoginResult result = service.authenticate(dto);

        //then
        assertEquals(LoginResult.SUCCESS, result);
    }

}