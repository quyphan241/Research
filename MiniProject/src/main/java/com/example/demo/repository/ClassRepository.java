package com.example.demo.repository;

import com.example.demo.model.Class;

import java.util.List;
import java.util.Optional;

public interface ClassRepository {
    int save(Class _class);
    int update(Class _class);
    int deleteById(Long id);
    List<Class> findAll();
    Class findById(Long id);

}
