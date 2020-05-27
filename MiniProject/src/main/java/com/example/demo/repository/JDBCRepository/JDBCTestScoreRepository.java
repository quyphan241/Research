package com.example.demo.repository.JDBCRepository;

import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.model.TestScore;
import com.example.demo.repository.TestScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class JDBCTestScoreRepository implements TestScoreRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(TestScore testScore) {
        String sql= "INSERT INTO testscore(firstscore, secondscore, finalScore, summaryScore, id_subject,id_student)" +
                " VALUES(?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,testScore.getFirstScore(), testScore.getSecondScore(),
                testScore.getFinalScore(), testScore.getSummaryScore(), testScore.getId_subject(), testScore.getId_student());
    }

    @Override
    public int update(TestScore testScore) {
        String sql= "UPDATE testScore SET firstscore = ?, secondscore=?, finalScore=?, id_subject=?, id_student=? WHERE id=?";
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

    @Override
    public List<TestScore> findAllByIdStudent(Long id) {
        String sql= "SELECT s.name as name_student,  sub.name as name_subject, sco.firstscore, sco.secondscore, sco.finalscore, sco.summaryscore FROM testscore sco " +
                "INNER JOIN students s ON sco.id_student = s.id INNER JOIN subjects sub ON sco.id_subject=sub.id WHERE s.id =" + id + " AND s.isDeleted = 0";

        List<TestScore> testScores = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            TestScore obj = new TestScore();
            obj.setName_student((String) row.get("name_student"));
            obj.setName_subject((String) row.get("name_subject"));
            obj.setFirstScore((double) row.get("firstscore"));
            obj.setSecondScore((double) row.get("secondscore"));
            obj.setFinalScore((double) row.get("finalscore"));
            obj.setSummaryScore((double) row.get("summaryscore"));
            testScores.add(obj);
        }
        return testScores;
    }

    @Override
    public List<TestScore> findAllByIdClassAndIdSubject(Long id_class, Long id_subject) {
//        String sql = "SELECT s.id as id_student, s.name as name_student, sco.firstscore, sco.secondscore, sco.finalscore, sco.summaryscore FROM students s " +
//                "INNER JOIN testscore sco ON s.id= sco.id_student WHERE s.id_class ="+ id_class+" AND sco.id_subject ="+ id_subject+" AND s.isDeleted=0";
        String sql = "SELECT s.id as id_student,sco.id as id_score, sco.id_student as sco_idstudent,sco.id_subject, s.id_class, s.name as name_student, sco.firstscore, sco.secondscore, sco.finalscore, sco.summaryscore FROM students s"
        + " LEFT JOIN testscore sco ON s.id= sco.id_student AND sco.id_subject="+id_subject+" WHERE s.id_class ="+ id_class+" AND s.isDeleted=0";

        List<TestScore> testScores = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
//            TestScore obj = new TestScore();
//            obj.setId_student((Long) row.get("id_student"));
//            obj.setName_student((String) row.get("name_student"));
//            obj.setFirstScore((double) row.get("firstscore"));
//            obj.setSecondScore((double) row.get("secondscore"));
//            obj.setFinalScore((double) row.get("finalscore"));
//            obj.setSummaryScore((double) row.get("summaryscore"));
//            testScores.add(obj);
            if(row.get("id_student")!=null && row.get("firstscore")!=null){
                TestScore obj = new TestScore();
                obj.setId((Long) row.get("id_score"));
                obj.setId_student((Long) row.get("id_student"));
                obj.setId_subject((Long) row.get("id_subject"));
                obj.setName_student((String) row.get("name_student"));
                obj.setFirstScore((double) row.get("firstscore"));
                obj.setSecondScore((double) row.get("secondscore"));
                obj.setFinalScore((double) row.get("finalscore"));
                obj.setSummaryScore((double) row.get("summaryscore"));
                testScores.add(obj);
            }
            else {
                TestScore obj = new TestScore(id_subject);
                obj.setId((Long) row.get("id_score"));
                obj.setId_student((Long) row.get("id_student"));
                obj.setName_student((String) row.get("name_student"));
                obj.setFirstScore(-1);
                obj.setSecondScore(-1);
                obj.setFinalScore(-1);
                obj.setSummaryScore(-1);
                testScores.add(obj);
            }
        }
        return testScores;
    }

    @Override
    public TestScore findByIdStudentAndIdSubject(Long id_student, Long id_subject) {
        String sql = "SELECT s.id as id_student, sco.id_student as sco_idstudent,sco.id_subject, s.id_class, s.name as name_student, sco.firstscore, sco.secondscore, sco.finalscore, sco.summaryscore FROM students s" +
                " INNER JOIN testscore sco ON s.id= sco.id_student WHERE sco.id_subject= " +id_subject+" AND sco.id_student =" +id_student;
        return (TestScore) jdbcTemplate.queryForObject(
                sql,
                new Object[]{id_student, id_subject},
                new BeanPropertyRowMapper(TestScore.class));
    }
}
