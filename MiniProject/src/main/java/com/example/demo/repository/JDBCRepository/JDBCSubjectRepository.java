package com.example.demo.repository.JDBCRepository;

import com.example.demo.model.Subject;
import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class JDBCSubjectRepository implements SubjectRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Subject subject) {
        String sql= "INSERT INTO subjects(name) VALUES(?)";
        return jdbcTemplate.update(sql,subject.getName());
    }

    @Override
    public int update(Subject subject) {
        String sql= "UPDATE subjects SET NAME = ? WHERE id = ?";
        return jdbcTemplate.update(sql, subject.getName(), subject.getId());
    }

    @Override
    public int deleteById(Long id) {
        String sql= "UPDATE subjects SET isDeleted=1 WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Subject> findAll() {
        String sql= "SELECT * FROM subjects WHERE isDeleted=0";
        List<Subject> subjects = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper(Subject.class));
        return subjects;
    }

    @Override
    public Subject findById(Long id) {
        String sql = "SELECT * FROM subjects WHERE id = ? AND isDeleted=0";
        return (Subject) jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper(Subject.class));
    }
}
