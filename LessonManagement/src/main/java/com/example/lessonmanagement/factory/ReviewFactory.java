package com.example.lessonmanagement.factory;

import com.example.lessonmanagement.model.Review;

public class ReviewFactory {
    public static Review createReview(String studentName, String tutorName, int rating, String comment) {
        return new Review(studentName, tutorName, rating, comment);
    }
}