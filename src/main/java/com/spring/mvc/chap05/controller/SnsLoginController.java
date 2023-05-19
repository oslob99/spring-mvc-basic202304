package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.service.SnsLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SnsLoginController {

    private final SnsLoginService loginService;

    @Value("${sns.kakao.app-key}")
    private String kakaoAppKey;

    @Value("${sns.kakao.redirect-uri}")
    private String kakaoRedirectURI;



    // 카카오 인가 코드 발급 요청
    @GetMapping("/kakao/login")
    public String kakaoLogin(){

        String requestUri = String.format("https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code",kakaoAppKey,kakaoRedirectURI);

        return "redirect:" + requestUri;
    }

    // 인가코드를 받아 토큰을 요청
    @GetMapping("/sns/kakao")
    public String snsKakao(String code, HttpSession session){
        log.info("인가코드: {}", code);

        // 인가코드를 가지고 카카오서버에 post요청을 보내야함
        Map<String, String> map = new HashMap<>();
        map.put("appkey",kakaoAppKey);
        map.put("redirect", kakaoRedirectURI);
        map.put("code",code);

        loginService.kakaoService(map,session);


        // 서버 -> 서버로 post요청 보내는법

        return "";
    }

}
