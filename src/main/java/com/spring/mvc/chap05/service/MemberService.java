package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.AutoLoginDTO;
import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.dto.response.LoginUserResponseDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;

import static com.spring.mvc.chap05.service.LoginResult.*;
import static com.spring.mvc.util.LoginUtil.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원가입 처리 서비스
    public boolean join(final SignUpRequestDTO dto) {

        // dto를 entity로 변환
        Member member = Member.builder()
                .account(dto.getAccount())
                .email(dto.getEmail())
                .name(dto.getName())
                .password(encoder.encode(dto.getPassword()))
                .build();

        // 매퍼에게 회원정보 전달해서 저장명령
        return memberMapper.save(member);
    }

    // 중복검사 서비스 처리
    public boolean checkSignUpValue(
            String type, String keyword) {

        int flagNum = memberMapper.isDuplicate(type, keyword);

        return flagNum == 1;
    }

    // 로그인 검증
    public LoginResult authenticate(LoginRequestDTO dto
                                    , HttpSession session
                                    , HttpServletResponse response) {

        Member foundMember = memberMapper.findMember(dto.getAccount());

        // 회원가입 여부 확인
        if (foundMember == null) {
            log.info("{} - 회원가입 안했음ㅋ", dto.getAccount());
            return NO_ACC;
        }
        // 비밀번호 일치 확인
        if (!encoder.matches(dto.getPassword(), foundMember.getPassword())) {
            log.info("비밀번호 불일치!");
            return NO_PW;
        }

        // 자동로그인 체크 여부 확인
        if (dto.isAutoLogin()){
            // 1. 쿠키 생성 - 쿠키값에 세션 아이디 저장
            Cookie autoLoginCookie = new Cookie(AUTO_LOGIN_COOKIE,session.getId());

            // 2. 쿠키 셋팅 - 수명이랑 사용경로
            int limitTime = 60 * 60 * 24 * 90;
            autoLoginCookie.setMaxAge(limitTime);
            autoLoginCookie.setPath("/"); // 전체 경로

            // 3. 쿠키를 클라이언트에 응답전송
            response.addCookie(autoLoginCookie);

            // 4. DB에도 쿠키에 저장된 값가 수명을 저장
            memberMapper.saveAutoLogin(AutoLoginDTO.builder()
                            .sessionId(session.getId())
                            .account(dto.getAccount())
                            .limitTime(LocalDateTime.now().plusDays(90))
                    .build());
        }

        log.info("{}님 로그인 성공!", foundMember.getName());
        return SUCCESS;

    }

    public void maintainLoginState(HttpSession session, String account) {
        // 로그인이 성공하면 세션에 로그인한 회원의 정보들을 저장
        /*
            로그인시 클라이언트에게 전달할 회원정보
            - 닉네임
            - 프로필사진
            - 마지막 로그인 시간
         */
        // 현재 로그인한 사람의 모든 정보
        Member member = getMember(account);

        // 현재 로그인한 사람의 화면에 보여줄 일부정보
        LoginUserResponseDTO dto = LoginUserResponseDTO.builder()
                .account(member.getAccount())
                .nickName(member.getName())
                .email(member.getEmail())
                .auth(member.getAuth().toString())
                .build();
        // 그 정보를 세션에 저장
        session.setAttribute(LOGIN_KEY, dto);
        // 세션의 수명을 설정
        session.setMaxInactiveInterval(60 * 60); // 1시간
    }

    // 멤버 정보를 가져오는 서비스기능
    public Member getMember(String account) {
        return memberMapper.findMember(account);
    }
}
