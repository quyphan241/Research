package com.example.demo.model;

public class Class {
    private Long id;
    private String name;
    private boolean isDeleted;
    private Long id_semester;

    public Class() {
    }

    public Class(Long id, String name, boolean isDeleted) {

    }

    public Class(Long id, String name) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Long getId_semester() {
        return id_semester;
    }

    public void setId_semester(Long id_semester) {
        this.id_semester = id_semester;
    }
}
