package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.dto.page.Search;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReplyMapperTest {

    @Autowired
    BoardMapper boardMapper;
    @Autowired
    ReplyMapper replyMapper;

    @Test
    @DisplayName("게시물 300개를 등록하고 각 게시물에 랜덤으로 100개의 댓글을 나눠 등록")
    void bulkInsertTest(){
        for (int i = 1; i <= 300; i++) {
            Board b = Board.builder()
                    .title("노잼"+i)
                    .content("꿀잼리지롱"+i)
                    .build();
            boardMapper.save(b);
        }
        assertEquals(300,boardMapper.count(new Search()));

        for (int i = 1; i <= 1000; i++) {
            Reply r = Reply.builder()
                    .replyWriter("노잼이" + i)
                    .replyText("멀똘멀뚱" + i)
                    .boardNo((long) (Math.random() * 300 + 1))
                    .build();
            replyMapper.save(r);
        }
    }

    @Test
    @DisplayName("댓글을 3번 게시물에 등록하면 3번 게시물의 총 댓글 수는 12개이어야한다")
    @Transactional
    @Rollback // 테스트 끝난 이후 롤백해라
    void saveTest(){
        // given
        long boardNo = 300L;
        Reply newReply = Reply.builder()
                .replyText("세이브")
                .replyWriter("헉힉")
                .boardNo(boardNo)
                .build();
        // when
        boolean flag = replyMapper.save(newReply);
        // then
        assertTrue(flag); // 세이브가 성공했을것이라고 단언
        assertEquals(301,replyMapper.count(boardNo));
    }

    @Test
    @DisplayName("댓글 번호가 1001번인 댓글을 삭제하면 " +
            "3번 게시물의 총 댓글 수가 10이어야 한다.")
    @Transactional @Rollback
    void removeTest() {
        //given
        long replyNo = 1001L;
        long boardNo = 3L;

        //when
        boolean flag = replyMapper.deleteOne(replyNo);

        //then
        assertTrue(flag);
        assertEquals(10, replyMapper.count(boardNo));
    }

    @Test
    void BulkReplyInsert(){

        for (int i = 1; i <= 300; i++) {
            Reply reply = Reply.builder()
                    .replyText("우우우우"+i)
                    .replyWriter("나야나"+i)
                    .boardNo(300L)
                    .build();
            replyMapper.save(reply);
        }
    }

}