package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.dto.page.Page;
import com.spring.mvc.chap05.dto.page.PageMaker;
import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;

    // 로그인 요청
    @GetMapping("/login")
    public String loginForm(){

        return "chap05/login";
    }

    @PostMapping("/login")
    public String loginReq(){

        return "";
    }

    // 목록 조회 요청
    @GetMapping("/list")
    public String list(Search page, Model model
                        , HttpServletRequest request) {
        boolean flag = false;

        try {
            InetAddress ipAddress = InetAddress.getLocalHost();
            log.info("현재 아이피: {}",ipAddress.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // 세션을 확인
        Object login = request.getSession().getAttribute("login");
        if (login != null) flag = true;


        // 쿠키를 확인
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("login")){
//                flag = true;
//                break;
//            }
//        }
        if (!flag) return "redirect:/members/sign-in";

        log.info("/board/list : GET");
        log.info("page : {}",page);
        List<BoardListResponseDTO> responseDTOS
                = boardService.getList(page);

        // 페이징 알고리즘
        PageMaker maker = new PageMaker(page, boardService.getCount(page));

        model.addAttribute("bList", responseDTOS);
        model.addAttribute("maker",maker);
        model.addAttribute("s",page);
        return "chap05/list";
    }

    // 글쓰기 화면 조회 요청
    @GetMapping("/write")
    public String write() {
        System.out.println("/board/write : GET");
        return "chap05/write";
    }

    // 글 등록 요청 처리
    @PostMapping("/write")
    public String write(BoardWriteRequestDTO dto) {
        System.out.println("/board/write : POST");
        boardService.register(dto);
        return "redirect:/board/list";
    }

    // 글 삭제 요청 처리
    @GetMapping("/delete")
    public String delete(int bno) {
        System.out.println("/board/delete : GET");
        boardService.delete(bno);
        return "redirect:/board/list";
    }

    // 글 상세 조회 요청
    @GetMapping("/detail")
    public String detail(int bno, @ModelAttribute("s") Search search, Model model) {
        System.out.println("/board/detail : GET");
        List<Reply> replyList = boardService.getReplyList(bno, search);
        model.addAttribute("b", boardService.getDetail(bno));
        model.addAttribute("rList",replyList);
//        model.addAttribute("s",search);
        return "chap05/detail";
    }

    // 글 수정 페이지 요청
    @GetMapping("/modify")
    public String goModify(int bno,Model model){
        model.addAttribute("b",boardService.getDetail(bno));
        return "chap05/modify";
    }

    // 글 수정 요청
    @PostMapping("/modify")
    public String modified(Board dto){
        boardService.modify(dto);
        System.out.println(dto.getBoardNo());
//        System.out.println("들옴");
        return "redirect:/board/list";
    }

    // 댓글 추가 페이지 요청
    @PostMapping("/reply")
    public String moveReply(Reply reply,Search search,Model model){
        System.out.println(reply);
        reply.setReplyWriter("하하하");
        boardService.saveReply(reply);
        model.addAttribute("r",reply);
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/board/detail")
                .queryParam("bno", reply.getBoardNo())
                .queryParam("pageNo", search.getPageNo())
                .queryParam("type", search.getType())
                .queryParam("keyword", search.getKeyword());

        return "redirect:" + builder.toUriString();
//        return String.format("redirect:/board/detail?bno=%d&pageNo=%d&type=%s&keyword=%s",reply.getBoardNo(),search.getPageNo(),search.getType(),search.getKeyword());
    }


}
