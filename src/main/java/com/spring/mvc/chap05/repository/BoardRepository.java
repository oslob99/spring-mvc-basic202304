package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;

import java.util.List;

public interface BoardRepository {

    // 게시물 목록 조회
    List<Board> findAll();
    // 게시물 상세 조회
    Board findOne(int BoardNo);
    // 게시물 등록
    boolean save(Board board);
    // 게시물 삭제
    boolean deleteByNo(int boardNo);
    // 조회수 상승

    // 게시물 수정

    // 게시물 정렬

    // 게시물 검색

}
