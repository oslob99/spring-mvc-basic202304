package com.spring.mvc.chap05.dto;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ReplyModifyRequestDTO {

    @NotNull
    @Min(0) @Max(Long.MAX_VALUE)
    private Long boardNo; // 게시글 번호
    @NotNull
    @Min(0) @Max(Long.MAX_VALUE)
    private Long replyNo; // 댓글 번호
    @NotBlank
    private String text; // 수정할 댓글

    public Reply toEntity(){
        return Reply.builder()
                .replyText(this.text)
                .replyNo(this.replyNo)
                .boardNo(this.boardNo)
                .build();
    }
}
