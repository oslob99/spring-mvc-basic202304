package com.spring.mvc.chap04.dto;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import lombok.*;

@RequiredArgsConstructor // final만 골라서 초기화
@Getter @ToString @EqualsAndHashCode
public class ScoreListResponseDTO {
    private final int stuNum;
    private final String maskingName; // 첫글자 빼고 *처리
    private final double average;
    private final Grade grade;

    public ScoreListResponseDTO(Score score) {
        this.stuNum = score.getStuNum();
        this.maskingName = makeMaskingName(score.getName());
        this.average = score.getAverage();
        this.grade = score.getGrade();
    }

    // 첫 글자만 빼고 다 *처리하기
    private String makeMaskingName(String name) {
        String maskingName = String.valueOf(name.charAt(0));
        for (int i = 1; i < name.length(); i++) {
            maskingName += "*";
        }
        return maskingName;
    }
}
