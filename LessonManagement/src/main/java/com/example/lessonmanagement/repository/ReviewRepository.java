package com.example.lessonmanagement.repository;

import com.example.lessonmanagement.model.Review;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    private static ReviewRepository instance;
    private final List<Review> reviews = new ArrayList<>();

    private ReviewRepository() {}

    public static ReviewRepository getInstance() {
        if (instance == null) {
            instance = new ReviewRepository();
        }
        return instance;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void clear() {
        reviews.clear();
    }


    public List<Review> getReviewsByTutor(String tutorName) {
        List<Review> tutorReviews = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getTutorName().equals(tutorName)) {
                tutorReviews.add(review);
            }
        }
        return tutorReviews;
    }


}
