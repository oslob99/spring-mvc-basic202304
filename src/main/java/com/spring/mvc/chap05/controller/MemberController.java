package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.LoginRequestDTO;
import com.spring.mvc.chap05.dto.SignUpRequestDTO;
import com.spring.mvc.chap05.service.LoginResult;
import com.spring.mvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import static com.spring.mvc.chap05.service.LoginResult.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 회원 가입 요청
    // 회원가입 양식 요청
    @GetMapping("/sign-up")
    public String signUp(){
        log.info("/member/sign-up GET - forwarding to jsp");

        return "members/sign-up";
    }

    // 회원가입 처리요청
    @PostMapping("/sign-up")
    public String signUp(SignUpRequestDTO dto){
        log.info("/members/sign-up POST ! - {}",dto);
        boolean flag = memberService.join(dto);
        return "redirect:/board/list";
    }

    // 아이디, 이메일 중복검사
    // 비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<?> check(String type, String keyword){
        log.info("/members/check?type={}&keyword={} ASYNC GET!", type, keyword);
        boolean flag = memberService.checkSignUpValue(type, keyword);
        return ResponseEntity.ok().body(flag);
    }

    // 로그인 양식 요청
    @GetMapping("/sign-in")
    public String signIn(HttpServletRequest request){
        log.info("/members/sign-in GET - forwarding to jsp");

        // 요청 정보 헤더 안에는 Referer라는 키가 있는데
        // 여기 값은 이 페이지로 들어올 때 어디에서 왔는지에 대한
        // URI정보가 기록되어있음
        String referer = request.getHeader("Referer");
        log.info("referer: {}",referer);

        return "members/sign-in";
    }
    // 로그인 검증 요청
    @PostMapping("/sign-in")
    public String signIn(LoginRequestDTO dto
                         // 리다이렉션시 2번째 응답에 데이터를 보내기 위함
            , RedirectAttributes attributes
            , HttpServletResponse response
            , HttpServletRequest request){
        log.info("/members/sign-up POST ! - {}",dto);

        LoginResult result = memberService.authenticate(dto);

        // 로그인 성공시
        if (result == SUCCESS) {

            // 서버에서 세션에 로그인 정보를 저장
            memberService.maintainLoginState(request.getSession(), dto.getAccount());

//            // 쿠키 만들기
//            Cookie loginCookie = new Cookie("login","홍길동");
//            // 쿠키 셋팅
//            loginCookie.setPath("/");
//            loginCookie.setMaxAge(60 * 60 * 24);
//
//            // 쿠키를 응답시에 실어서 클라이언트에게 전송
//            response.addCookie(loginCookie);

            return "redirect:/";
        }
        // 1회용으로 쓰고 버릴 데이터
        attributes.addFlashAttribute("msg",result);
        // 로그인 실패시
        return "redirect:/members/sign-in";

    }

    // 로그아웃 요청 처리
    @GetMapping("/sign-out")
    public String signOut(HttpSession session){
        // 세션에서 login정보를 제거
        session.removeAttribute("login");

        // 세션을 아예 초기화 (세션만료 시간)
        session.invalidate();

        return "redirect:/";
    }


}
