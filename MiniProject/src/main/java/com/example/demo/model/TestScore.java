package com.example.demo.model;

public class TestScore {
    private Long id;
    private double firstScore;
    private double secondScore;
    private double finalScore;
    private double summaryScore;
    private Long id_subject;
    private Long id_student;
    private String name_subject;
    private String name_student;
    private boolean isDeleted;

    public TestScore(Long id_subject) {
        this.id_subject = id_subject;
    }

    public TestScore(Long id, double firstScore, double secondScore, double finalScore, String name_student) {
        this.id = id;
        this.firstScore = firstScore;
        this.secondScore = secondScore;
        this.finalScore = finalScore;
        this.name_student = name_student;
    }

    public TestScore() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getFirstScore() {
        return firstScore;
    }

    public void setFirstScore(double firstScore) {
        this.firstScore = firstScore;
    }

    public double getSecondScore() {
        return secondScore;
    }

    public void setSecondScore(double secondScore) {
        this.secondScore = secondScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public Long getId_subject() {
        return id_subject;
    }

    public void setId_subject(Long id_subject) {
        this.id_subject = id_subject;
    }

    public Long getId_student() {
        return id_student;
    }

    public void setId_student(Long id_student) {
        this.id_student = id_student;
    }

    public double getSummaryScore() {
        return summaryScore;
    }

    public void setSummaryScore(double summaryScore) {
        this.summaryScore = summaryScore;
    }

    public String getName_subject() {
        return name_subject;
    }

    public void setName_subject(String name_subject) {
        this.name_subject = name_subject;
    }

    public String getName_student() {
        return name_student;
    }

    public void setName_student(String name_student) {
        this.name_student = name_student;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
