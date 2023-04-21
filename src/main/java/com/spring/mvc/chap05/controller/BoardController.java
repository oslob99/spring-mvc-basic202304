package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardNewInsertDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model){
        System.out.println("하이");
        List<BoardListResponseDTO> responseDTOS = boardService.showAll();
        model.addAttribute("boardList",responseDTOS);

        return "chap05/list";
    }

    @GetMapping("/write")
    public String write(){
        return "chap05/write";
    }

    @PostMapping("/write")
    public String insert(BoardNewInsertDTO dto){
        boardService.saveOne(dto);
        return "redirect:/board/list";
    }

    @GetMapping("/remove")
    public String remove(int boardNo){
        System.out.println("삭제");
        boardService.remove(boardNo);
        return "redirect:/board/list";
    }

    @GetMapping("/detail")
    public String detail(int boardNo,Model model){
        Board board = boardService.showOne(boardNo);
        model.addAttribute("b",board);
        return "chap05/detail";
    }

}
