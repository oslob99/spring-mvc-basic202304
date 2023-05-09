package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper mapper;

    @Autowired
    PasswordEncoder encoder;

    @Test
    @DisplayName("회원가입에 성공해야한다")
    void registerTest(){
        Member member = Member.builder()
                .account("hi")
                .password(encoder.encode("1234"))
                .name("바부")
                .email("azx@naver.com")
                .build();
        boolean flag = mapper.save(member);
        assertTrue(flag);
    }

    @Test
    @DisplayName("peach라는 계정명으로 회원을 조회허면 그 회원의 이름이 복숭아여야한다")
    void findMemberTest(){
        // given
        String acc = "peach";

        //when
        Member foundMember = mapper.findMember(acc);

        // then
        System.out.println("foundMember = " + foundMember);
        assertEquals("천둥복숭아",foundMember.getName());
    }

    @Test
    @DisplayName("peach라는 계정이 있으면 결과값이 1이 나와야한다")
    void accountDuplicateTest(){
        // given
        String acc = "peach";

        // when
        int count = mapper.isDuplicate("account", acc);

        // then
        assertEquals(1,count);
    }

    @Test
    @DisplayName("peach@naver.com라는 이메일이 있으면 결과값이 1이 나와야한다")
    void emailDuplicateTest(){
        // given
        String email = "peach@naver.com";

        // when
        int count = mapper.isDuplicate("email", email);

        // then
        assertEquals(1,count);
    }

    @Test
    @DisplayName("비밀번호가 암호화되어야한다")
    void encodingTest(){

        // 인코딩 전 패스워드
        String rawPassword = "abc1234!";

        // 인코딩 후 패스워드
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("rawPassword = " + rawPassword);
        System.out.println("encodedPassword = " + encodedPassword);
    }


}