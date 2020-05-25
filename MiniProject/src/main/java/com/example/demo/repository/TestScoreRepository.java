package com.example.demo.repository;

import com.example.demo.model.TestScore;

import java.util.List;

public interface TestScoreRepository {
    int save(TestScore testScore);
    int update(TestScore testScore);
    int deleteById(Long id);
    List<TestScore> findAll();
    TestScore findById(Long id);
    List<TestScore> findAllByIdStudent(Long id);
    List<TestScore> findAllByIdClassAndIdSubject(Long id_class, Long id_subject);
}
