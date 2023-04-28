package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.BoardModifyDTD;
import com.spring.mvc.chap05.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 게시물 목록 조회
    List<Board> findAll();

    // 게시물 상세 조회
    Board findOne(int boardNo);

    // 게시물 등록
    boolean save(Board board);

    // 게시물 삭제
    boolean deleteByNo(int boardNo);

    // 조회수 상승
    void upViewCount(int boardNo);

    // 게시물 수정
    boolean modifyByNo(Board dto);
}
