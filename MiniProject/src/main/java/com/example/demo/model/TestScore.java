package com.example.demo.model;

public class TestScore {
    private Long id;
    private float firstScore;
    private float secondScore;
    private float finalScore;
    private float summaryScore;
    private Long id_subject;
    private Long id_student;
    private boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getFirstScore() {
        return firstScore;
    }

    public void setFirstScore(float firstScore) {
        this.firstScore = firstScore;
    }

    public float getSecondScore() {
        return secondScore;
    }

    public void setSecondScore(float secondScore) {
        this.secondScore = secondScore;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(float finalScore) {
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

    public float getSummaryScore() {
        return summaryScore;
    }

    public void setSummaryScore(float summaryScore) {
        this.summaryScore = summaryScore;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
