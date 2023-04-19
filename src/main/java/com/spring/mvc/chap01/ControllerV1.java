package com.spring.mvc.chap01;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

// 어떤 요청들을 처리할지 공통 URL을 설계
@RequestMapping("/spring/*")
// 이 클래스의 객체를 스프링이 관리하도록 빈을 등록
@Controller // @Component와 같은 개념
public class ControllerV1 {

    // 세부 요청들은 메서드를 통해 처리
    @RequestMapping("/hello") // http://localhost:8181/spring/hello
    public String hello(){
        System.out.println("\n===== 헬로 요청이 들어옴! =====\n");
        // 어떤 JSP를 열지 경로를 적는다
        return "hello";
    }

    // /spring/food 요청이 오면 food.jsp를 열어보세요
    @RequestMapping("/food")
    public String food(){
        System.out.println("food요청 들어옴");
        return "chap01/food";
    }
     // ====== 요청 파라미터 읽기 (Query String parameter) =====
    // == 1. HttpServletRequest 사용하기
    // ==> ex ) /spring/person?name=kim&age=30

    @RequestMapping("/person")
    public String person(HttpServletRequest request){
        String name = request.getParameter("name");
        String age = request.getParameter("age");

        System.out.println("name = " + name);
        System.out.println("age = " + age);

        return "";
    }

    // == 2. @RequestParam 사용
    // ==> ex ) /spring/major?stu=kim&major=business&grade=3
    @RequestMapping("/major")
    public String major(
            String stu,
            @RequestParam("major") String mj,
            @RequestParam(defaultValue = "1") int grade
    ){
        System.out.println("stu = " + stu);
        System.out.println("mahor = " + mj);
        System.out.println("grade = " + grade);

        return "";
    }

}
