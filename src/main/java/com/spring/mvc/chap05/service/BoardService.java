package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardNewInsertDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 중간 처리 기능 자유롭게 사용
    public List<BoardListResponseDTO> showAll(){
        return boardRepository.findAll()
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(toList())
                ;
    }

    public Board showOne(int boardNo){
        return boardRepository.findOne(boardNo);
    }

    public boolean saveOne(BoardNewInsertDTO dto) {
        return boardRepository.save(new Board(dto));
    }

    public boolean remove(int boardNo) {
        boardRepository.deleteByNo(boardNo);

        return false;
    }
}
