package com.spring.mvc.chap01;

import lombok.*;

// DTO (Data Transfer Object)
// 클라이언트의 요청데이터를 서버에서 받을 때
// 서버의 응답데이터를 클라이언트로 보낼 때 사용하는 객체
@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    // 필드명이 클라이언트가 보낸 쿼리스트링 이름과 같아야함
//    세터와 기본생성자가 잇어야 정상 작동
   private String oNum;
   private String goods;
   private int amount;
   private int price;


}
