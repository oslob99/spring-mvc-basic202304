package com.spring.mvc.chap05.dto;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BoardModifyDTD {

    private int boardNo; // 게시글 번호
    private String title;
    private String content;
}


