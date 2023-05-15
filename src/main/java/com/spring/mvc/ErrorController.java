package com.spring.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/404")
    public String err404(HttpServletRequest request, RedirectAttributes ra) {
        log.warn("404 에러!!");
        ra.addFlashAttribute("url", request.getHeader("referer"));
        return "error/error404";
    }
    @GetMapping("/500")
    public String err500() {
        log.warn("500 에러!!");
        return "error/error500";
    }
}
