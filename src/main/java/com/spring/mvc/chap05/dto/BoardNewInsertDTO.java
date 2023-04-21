package com.spring.mvc.chap05.dto;

import lombok.*;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardNewInsertDTO {
    private String title;
    private String content;
}
