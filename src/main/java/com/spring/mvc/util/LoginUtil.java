package com.spring.mvc.util;

import com.spring.mvc.chap05.dto.response.LoginUserResponseDTO;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpSession;

// 회원 인증 인가 관련 상수와 메서드를 가진 객체
@UtilityClass
public class LoginUtil {

    // 로그인 세션 키
    public  static final String LOGIN_KEY = "login";

    // 로그인 여부 확인
    public boolean isLogin(HttpSession session){
        return session.getAttribute(LOGIN_KEY) != null;

    }

    // 로그인한 사람의 계정명을 반환하는 메서드
    public static  String getCurrentLoginMemberAccount(HttpSession session){
        LoginUserResponseDTO loginUserInfo = (LoginUserResponseDTO) session.getAttribute(LOGIN_KEY);

        return loginUserInfo.getAccount();
    }

    // 관리자인지 확인해주는 메서드
    public static boolean isAdmin(HttpSession session){

        LoginUserResponseDTO loginUser = (LoginUserResponseDTO) session.getAttribute(LOGIN_KEY);
        return loginUser.getAuth().equals("ADMIN");
    }

    public static boolean isMine(HttpSession session, String targetAccount) {
        return targetAccount.equals(getCurrentLoginMemberAccount(session));
    }

    // 내가 쓴 게시물인지 확인해주는 메소드
    // 로그인한 사람 계정명과 실제 게시물 계정명

}
