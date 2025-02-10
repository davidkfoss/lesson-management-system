package com.example.lessonmanagement.model;

import java.util.List;

public class User {
    private String name;
    private String role; // "Student" or "Tutor"
    private List<String> studyFields; // Only relevant for tutors

    public User(String name, String role, List<String> studyFields) {
        this.name = name;
        this.role = role;
        this.studyFields = studyFields;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public List<String> getStudyFields() {
        return studyFields;
    }

    public void setStudyFields(List<String> studyFields) {
        this.studyFields = studyFields;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", studyFields=" + studyFields +
                '}';
    }
}