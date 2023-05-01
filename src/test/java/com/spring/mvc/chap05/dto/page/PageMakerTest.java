package com.spring.mvc.chap05.dto.page;

import org.junit.jupiter.api.Test;

class PageMakerTest {

    @Test
    void pageTest(){
        // 클라이언트의 페이지 정보
        Page page = new Page();
        page.setPageNo(2);
        page.setAmount(5);

        PageMaker maker = new PageMaker(page, 15);
        System.out.println("maker = " + maker);
    }
}