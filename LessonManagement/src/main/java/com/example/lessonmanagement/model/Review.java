package com.example.lessonmanagement.model;

public class Review {
    private String studentName;
    private String tutorName;
    private int rating;
    private String comment;

    public Review(String studentName, String tutorName, int rating, String comment) {
        this.studentName = studentName;
        this.tutorName = tutorName;
        this.rating = rating;
        this.comment = comment;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getTutorName() {
        return tutorName;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }
}