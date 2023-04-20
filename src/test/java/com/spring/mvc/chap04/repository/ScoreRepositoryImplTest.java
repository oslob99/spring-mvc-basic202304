package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreRepositoryImplTest {

    ScoreRepository repository = new ScoreRepositoryImpl();
    String sortedNum = "1";
    // 단위 테스트 (Unit Test)
    // 테스트 시나리오
    // - 단언(Assertion) 기법
    @Test
    @DisplayName("저장소에서 findAll을 호출하면 그 반환된 리스트에는 성적정보가 3개 있어야한다")
    void findAllTest(){
        // given-when-then 패턴
        // given : 테스트를 위해 주어질 데이터 (ex: parameter)

        // when : 테스트 실제 상황
        List<Score> scoreList = repository.findAll(sortedNum);

        // then : 테스트 결과 확인
        System.out.println(scoreList.size() == 3);

        // 나는 스코어리스트의 사이즈가 3인 것이 참이라고 단언하다
//        assertTrue(scoreList.size() == 3);
        assertEquals(5,scoreList.size());
        // 스코어 리스트의 첫번째 객체의 이름이 신짱구라고 단언한다
        assertEquals("신짱구",scoreList.get(0).getName());
    }


    @Test
    @DisplayName("저장소에서 findByStuNum을 호출해 학번이 2인 학생을 조회하면"
            +"그 학생의 국어점수가 20점이고 이름이 신형만이어야한다")
    void findOneTest(){
        // given
        int stuNum = 2;
        // when
        Score score = repository.findByStuNum(stuNum);
        // then
        assertEquals(20, score.getKor());
        assertEquals("신형만", score.getName());
    }

    @Test
    @DisplayName("저장소에서 findByStuNum을 호출해 학번이 99인 학생을 조회하면"
            +"null이 리턴될것이다")
    void findOneTest2(){
        // given
        int stuNum = 99;
        // when
        Score score = repository.findByStuNum(stuNum);
        // then
        assertNull(score);
    }

    @Test
    @DisplayName("저장소에서 학번이 2인 학생을 삭제한 후에" +
            "리스트를 전체 조회해보면 성적의개수가 2개일 것이고" +
            "다시 2번 학생을 조회했을 때 null이 반환되어야한다")
    void deleteTest(){
        // given
        int stuNum = 2;
        // when
        repository.deleteBy(stuNum);
        List<Score> scoreList = repository.findAll(sortedNum);
        Score score = repository.findByStuNum(stuNum);
        // then
        assertEquals(2, scoreList.size());
        assertNull(score);

        for (Score s : scoreList) {
            System.out.println("s = " + s);
        }
    }

    @Test
    @DisplayName("새로운 성적정보를 save를 통해 추가하면" +
            "목록의 개수가 4개여야 한다.")
    void saveTest(){
        // given
        Score score = new Score();
        score.setName("언년이");
        score.setKor(49);
        score.setMath(11);
        score.setEng(19);
        // when
        boolean flag = repository.save(score);
        List<Score> scoreList = repository.findAll(sortedNum);

        // then
        assertEquals(4,scoreList.size());
        assertTrue(flag);
        assertEquals(4,scoreList.get(scoreList.size()-1).getStuNum());
    }

}