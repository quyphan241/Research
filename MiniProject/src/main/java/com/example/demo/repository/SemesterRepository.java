package com.example.demo.repository;

import com.example.demo.model.Class;
import com.example.demo.model.Semester;

import java.util.List;

public interface SemesterRepository {
    int save(Semester semester);
    int update(Semester semester);
    int deleteById(Long id);
    List<Semester> findAll();
    Semester findById(Long id);
}
