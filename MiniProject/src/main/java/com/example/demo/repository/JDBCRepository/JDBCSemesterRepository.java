package com.example.demo.repository.JDBCRepository;

import com.example.demo.model.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Semester;
import com.example.demo.repository.SemesterRepository;

import java.util.List;

@Repository
public class JDBCSemesterRepository implements SemesterRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Semester semester) {
        String sql= "INSERT INTO semesters(name) VALUES(?)";
        return jdbcTemplate.update(sql,semester.getName());
    }

    @Override
    public int update(Semester semester) {
        String sql= "UPDATE semesters SET NAME = ? WHERE id = ?";
        return jdbcTemplate.update(sql, semester.getName(), semester.getId());
    }

    @Override
    public int deleteById(Long id) {
        String sql= "UPDATE semesters SET isDeleted=1 WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Semester> findAll() {
        String sql= "SELECT * FROM semesters WHERE isDeleted=0";
        List<Semester> semesters = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper(Semester.class));
        return semesters;
    }

    @Override
    public Semester findById(Long id) {
        String sql = "SELECT * FROM semesters WHERE id = ? AND isDeleted=0";
        return (Semester) jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper(Semester.class));
    }
}
