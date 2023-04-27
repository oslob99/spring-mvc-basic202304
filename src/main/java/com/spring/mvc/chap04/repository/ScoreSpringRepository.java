package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("spring")
@RequiredArgsConstructor
public class ScoreSpringRepository implements ScoreRepository{
    private final JdbcTemplate jdbcTemplate;

    private static Score mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Score(rs);
    }

    @Override
    public List<Score> findAll() {
        return null;
    }

    @Override
    public List<Score> findAll(String sort) {
        return jdbcTemplate.query("SELECT * FROM tbl_score", ScoreSpringRepository::mapRow);
    }

    @Override
    public boolean save(Score score) {
        String sql = "INSERT INTO tbl_score " +
                " (name, kor, eng, math, total, average, grade) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, score.getName(), score.getKor()
                , score.getEng(), score.getMath(), score.getTotal()
                , score.getAverage(), String.valueOf(score.getGrade()));

        return result == 1;
    }

    @Override
    public boolean deleteByStuNum(int stuNum) {
        String sql = "DELETE FROM tbl_score WHERE stu_num=?";
        int result = jdbcTemplate.update(sql, stuNum);
        return result == 1;
    }

    @Override
    public Score findByStuNum(int stuNum) {
        String sql = "SELECT * FROM tbl_score WHERE stu_num = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Score(rs),stuNum);
    }

    @Override
    public boolean modifyScore(ScoreRequestDTO dto){
        String sql = "UPDATE tbl_score set kor=?, eng=?, math=? WHERE stu_num=?";
        int result = jdbcTemplate.update(sql, dto.getKor(), dto.getEng(), dto.getMath(), dto.getStuNum());
        return result == 1;
    }

}
