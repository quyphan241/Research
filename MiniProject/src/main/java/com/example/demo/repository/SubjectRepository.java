package com.example.demo.repository;

import com.example.demo.model.Semester;
import com.example.demo.model.Subject;

import java.util.List;

public interface SubjectRepository {
    int save(Subject subject);
    int update(Subject subject);
    int deleteById(Long id);
    List<Subject> findAll();
    Subject findById(Long id);
}
