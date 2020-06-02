package com.example.demo.repository.JDBCRepository;

import com.example.demo.model.Class;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class JDBCStudentRepository implements StudentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Student student) {
        String sql= "INSERT INTO students(name, birthdate, gender, student_code, id_class) VALUES(?,?,?,?,?)";
        return jdbcTemplate.update(sql,student.getName(), student.getBirthDate(), student.getGender(), student.getStudentCode(), student.getId_class()) ;
    }

    @Override
    public int update(Student student) {
        String sql= "UPDATE students SET name = ?,birthdate = ?, gender = ?, student_code = ?, id_class = ? WHERE id = ?";
        return jdbcTemplate.update(sql, student.getName(), student.getBirthDate(), student.getGender(), student.getStudentCode(), student.getId_class(), student.getId());
    }

    @Override
    public int deleteById(Long id) {
        String sql= "UPDATE students SET isDeleted=1 WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Student> findAll() {
        String sql= "SELECT s.id as id, s.name as name, s.birthdate as birthdate, s.gender as gender,  s.student_code as student_code, c.name as name_class FROM students s" +
                " INNER JOIN classes c ON s.id_class= c.id WHERE s.isDeleted=0 ORDER BY name_class, name";
        List<Student> students = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Student obj = new Student();
            obj.setId((Long) row.get("id"));
            obj.setName((String) row.get("name"));
            obj.setBirthDate((Date) row.get("birthDate"));
            obj.setGender((String) row.get("gender"));
            obj.setStudentCode((Long) row.get("student_code"));
            obj.setName_class((String) row.get("name_class"));
            students.add(obj);
        }
        return students;
    }

    @Override
    public Student findById(Long id) {
        String sql = "SELECT s.id as id, s.name as name, s.birthdate as birthdate, s.gender as gender,  s.student_code as student_code, c.name as name_class FROM students s " +
                "INNER JOIN classes c ON s.id_class= c.id WHERE s.id = ? AND s.isDeleted=0";
        return (Student) jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper(Student.class));

    }

    @Override
    public List<Student> findAllByIdClass(Long id) {
        String sql= "SELECT s.id as id, s.name as name, s.birthdate as birthdate, s.gender as gender,  s.student_code as student_code, c.name as name_class FROM students s" +
                " INNER JOIN classes c ON s.id_class= c.id WHERE s.isDeleted=0 AND id_class =" + id + "ORDER BY name";
        List<Student> students = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Student obj = new Student();
            obj.setId((Long) row.get("id"));
            obj.setName((String) row.get("name"));
            obj.setBirthDate((Date) row.get("birthDate"));
            obj.setGender((String) row.get("gender"));
            obj.setStudentCode((Long) row.get("student_code"));
            obj.setName_class((String) row.get("name_class"));
            students.add(obj);
        }
        return students;
    }
}
