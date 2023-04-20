package com.spring.mvc.chap04.dto;


import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ScoreUpdateDTO {
    private int stuNum;
    private int kor, eng, math;

}
