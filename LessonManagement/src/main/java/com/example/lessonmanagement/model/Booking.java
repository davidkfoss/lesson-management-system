package com.example.lessonmanagement.model;

public class Booking {
    private String studentName;
    private String lessonTitle;
    private String turotName;

    public Booking(String studentName, String lessonTitle, String tutorName) {
        this.studentName = studentName;
        this.lessonTitle = lessonTitle;
        this.turotName = tutorName;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public String getTutorName() {
        return turotName;
    }
}