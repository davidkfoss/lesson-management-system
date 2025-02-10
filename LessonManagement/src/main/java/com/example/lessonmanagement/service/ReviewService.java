package com.example.lessonmanagement.service;

import com.example.lessonmanagement.model.Review;
import com.example.lessonmanagement.repository.ReviewRepository;
import com.example.lessonmanagement.repository.BookingRepository;

import java.util.List;

public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;

    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
    }

    public void addReview(Review review) {
        boolean hasAlreadyReviewed = reviewRepository.getReviewsByTutor(review.getTutorName()).stream()
                .anyMatch(existingReview -> existingReview.getStudentName().equals(review.getStudentName()));

        if (hasAlreadyReviewed) {
            System.out.println("You have already reviewed this tutor.");
            return;
        }

        boolean hasBookedLesson = bookingRepository.getBookings().stream()
                .anyMatch(booking -> booking.getStudentName().equals(review.getStudentName())
                        && booking.getTutorName().contains(review.getTutorName()));

        if (hasBookedLesson) {
            reviewRepository.addReview(review);
            System.out.println("Review added successfully.");
        } else {
            System.out.println("You can only review tutors you have taken lessons with.");
        }
    }

    public void viewTutorProfile(String tutorName) {
        List<Review> reviews = reviewRepository.getReviewsByTutor(tutorName);
        if (reviews.isEmpty()) {
            System.out.println("No reviews found for this tutor.");
        } else {
            System.out.println("Reviews for " + tutorName + ":");
            reviews.forEach(review -> System.out.println(review.getStudentName() + " (" + review.getRating() + "/5): " + review.getComment()));
        }
    }
}