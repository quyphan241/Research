package com.example.demo.repository.JDBCRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Class;
import com.example.demo.repository.ClassRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JDBCClassRepository implements ClassRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Class _class) {
        String sql= "INSERT INTO classes(name, id_semester) VALUES(?,?)";
        return jdbcTemplate.update(sql,_class.getName(),_class.getId_semester());
    }

    @Override
    public int update(Class _class) {
        String sql= "UPDATE classes SET name = ? WHERE id=?";
        return jdbcTemplate.update(sql, _class.getName(), _class.getId());
    }

    @Override
    public int deleteById(Long id) {
        String sql= "UPDATE classes SET isDeleted=1 WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Class> findAll() {
        String sql= "SELECT c.id as id,c.name as name ,s.name as name_semester FROM classes c " +
                "INNER JOIN semesters s ON c.id_semester= s.id WHERE c.isDeleted=0";
//        List<Class> classes = jdbcTemplate.query(
//                sql,
//                new BeanPropertyRowMapper(Class.class));
//        return classes;
        List<Class> classes = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        for (Map row : rows) {
            Class obj = new Class();
            obj.setId(((Long) row.get("ID")));
            obj.setName((String) row.get("NAME"));
            obj.setName_semester((String) row.get("name_semester"));
            classes.add(obj);
        }
        return classes;
    }

    @Override
    public Class findById(Long id) {
        String sql = "SELECT * FROM classes WHERE id = ? AND isDeleted=0";
        return (Class) jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper(Class.class));
    }
}