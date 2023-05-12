package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.repository.BoardMapper;
import com.spring.mvc.chap05.repository.ReplyMapper;
import com.spring.mvc.util.LoginUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class BoardService {

    //    private final BoardRepository boardRepository;
    private final BoardMapper boardRepository;
    private final ReplyMapper replyMapper;

    // 중간처리 기능 자유롭게 사용
    // 목록 중간처리
    public List<BoardListResponseDTO> getList(Search page) {

        return boardRepository.findAll(page)
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(toList())
                ;
    }

    // 글 등록 중간처리
    public boolean register(BoardWriteRequestDTO dto, HttpSession session) {
        Board board = new Board(dto);

        board.setAccount(LoginUtil.getCurrentLoginMemberAccount(session));

        return boardRepository.save(board);
    }

    public boolean delete(int bno) {
        return boardRepository.deleteByNo(bno);
    }

    public BoardDetailResponseDTO getDetail(int bno) {

        Board board = boardRepository.findOne(bno);
        // 조회수 상승 처리
//        board.setViewCount(board.getViewCount() + 1);
        boardRepository.upViewCount(bno);
        return new BoardDetailResponseDTO(board);
    }

    // 글 수정기능
    public boolean modify(Board dto){
        return boardRepository.modifyByNo(dto);
    }

    public int getCount(Search search) {
        return boardRepository.count(search);
    }

    // ------------------ 댓글 기능

    public boolean saveReply(Reply reply){
        return replyMapper.save(reply);
    }

    public List<Reply> getReplyList(int bno, Search search) {
        List<Reply> replyList = replyMapper.findAll(bno, search);
        return replyList;
    }
}
