package com.example.demo.repository.JDBCRepository;

import com.example.demo.model.Subject;
import com.example.demo.model.TestScore;
import com.example.demo.repository.TestScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCTestScoreRepository implements TestScoreRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(TestScore testScore) {
        String sql= "INSERT INTO testscore(score1, score2, finalScore, summaryScore, id_subject,id_student)" +
                " VALUES(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,testScore.getFirstScore(), testScore.getSecondScore(),
                testScore.getFinalScore(), testScore.getSummaryScore(), testScore.getId_subject(), testScore.getId_student());
    }

    @Override
    public int update(TestScore testScore) {
        String sql= "UPDATE testScore SET score1 = ?, score2=?, finalScore=?, id_subject=?, id_student=? WHERE id=?";
        return jdbcTemplate.update(sql, testScore.getFirstScore(),testScore.getSecondScore(),testScore.getFinalScore(),
                testScore.getId_subject(), testScore.getId_subject(),testScore.getId());
    }

    @Override
    public int deleteById(Long id) {
        String sql= "UPDATE testscore SET isDeleted=1 WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<TestScore> findAll() {
        String sql= "SELECT * FROM testscore WHERE isDeleted=0";
        List<TestScore> testScores = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper(TestScore.class));
        return testScores;
    }

    @Override
    public TestScore findById(Long id) {
        String sql = "SELECT * FROM testscore WHERE id = ? AND isDeleted=0";
        return (TestScore) jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper(TestScore.class));
    }
}
