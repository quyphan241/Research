package com.example.demo.repository.JDBCRepository;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCStudentRepository implements StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Student student) {
        String sql= "INSERT INTO students(name, birthdate, gender, id_class) VALUES(?,?,?,?)";
        return jdbcTemplate.update(sql,student.getName(), student.getBirthDate(), student.getGender(), student.getId_class()) ;
    }

    @Override
    public int update(Student student) {
        String sql= "UPDATE students SET NAME = ?,  birthdate = ?, gender = ?, id_class=?  WHERE id = ?";
        return jdbcTemplate.update(sql, student.getName(), student.getBirthDate(), student.getGender(), student.getId_class(), student.getId());
    }

    @Override
    public int deleteById(Long id) {
        String sql= "UPDATE students SET isDeleted=1 WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Student> findAll() {
        String sql= "SELECT * FROM students WHERE isDeleted=0";
        List<Student> students = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper(Student.class));
        return students;
    }

    @Override
    public Student findById(Long id) {
        String sql = "SELECT * FROM students WHERE id = ? AND isDeleted=0";
        return (Student) jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper(Student.class));
    }
}
