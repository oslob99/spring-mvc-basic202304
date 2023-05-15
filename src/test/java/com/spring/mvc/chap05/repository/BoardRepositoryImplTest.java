package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BoardRepositoryImplTest {


    @Autowired BoardRepository repository;

    @Test
    void saveTest() {
        Board b = new Board(11, "ddd", "fff");
        repository.save(b);
    }
}