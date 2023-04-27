package com.spring.mvc.chap04.dto;

import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScoreRequestDTO {

    private String name; // 학생 이름
    private int kor, eng, math; // 국, 영, 수 점수
    private int stuNum; // 학생번호

    public ScoreRequestDTO(String name, int kor, int eng, int math) {
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }

//    public ScoreRequestDTO(int kor, int eng, int math, int stuNum) {
//        this.kor = kor;
//        this.eng = eng;
//        this.math = math;
//        this.stuNum = stuNum;
//    }
}
