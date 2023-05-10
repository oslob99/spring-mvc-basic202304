package com.spring.mvc.chap05.service;

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

}