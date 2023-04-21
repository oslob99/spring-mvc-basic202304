package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Board;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.spring.mvc.chap05.dto.BoardListResponseDTO.makePrettierDateString;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailDTO {

    private int boardNo;
    private String title; // 제목
    private String content; // 내용
    private String date; // 날짜패턴 yyyy-MM-dd HH:mm

    public BoardDetailDTO(Board board){
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.date = makePrettierDateString(board.getRegDateTime());
    }

}
