package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.dto.ScoreUpdateDTO;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

@Repository // 스프링 빈 등록 : 객체 생성의 제어권을 스프링에게 위임
public class ScoreRepositoryImpl implements ScoreRepository{

    // key : 학번, value : 성적정보
    private static final Map<Integer,Score> scoreMap;

    // 학번에 사용할 일련번호
    private static int sequence;

    static {
        scoreMap = new HashMap<>();
        Score stu1 = new Score(new ScoreRequestDTO("신짱구",100,39,71));
        Score stu2 = new Score(new ScoreRequestDTO("신형만",20,99,61));
        Score stu3 = new Score(new ScoreRequestDTO("신짱아",80,19,21));

        stu1.setStuNum(++sequence);
        stu2.setStuNum(++sequence);
        stu3.setStuNum(++sequence);

        scoreMap.put(stu1.getStuNum(),stu1);
        scoreMap.put(stu2.getStuNum(),stu2);
        scoreMap.put(stu3.getStuNum(),stu3);
    }

    @Override
    public List<Score> findAll() {
        return scoreMap.values().stream()
                .sorted(comparing(Score::getStuNum)).collect(Collectors.toList());
    }

    @Override
    public List<Score> findAll(String sortedNum) {
        Comparator<Score> comparing = comparing(Score::getStuNum);
        switch (sortedNum){
            case "name":
                comparing = comparing(Score::getName);
                break;
            case "avg":
                comparing = comparing(Score::getAverage).reversed();
        }
        return scoreMap.values().stream()
                .sorted(comparing).collect(Collectors.toList());
    }

    @Override
    public boolean save(Score score) {
        if (scoreMap.containsKey(score.getStuNum())){
            return false;
        }
        score.setStuNum(++sequence);
        scoreMap.put(score.getStuNum(),score);
        return true;
    }

    @Override
    public boolean deleteBy(int stuNum) {
        if (!scoreMap.containsKey(stuNum)) return false;
        scoreMap.remove(stuNum);
        return true;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        return scoreMap.get(stuNum);
    }

    @Override
    public Score modifyByStuNum(ScoreUpdateDTO dto) {

        Score score = scoreMap.get(dto.getStuNum());
        score.setKor(dto.getKor());
        score.setEng(dto.getEng());
        score.setMath(dto.getMath());
        score.callCalc();

        return score;
    }
}
