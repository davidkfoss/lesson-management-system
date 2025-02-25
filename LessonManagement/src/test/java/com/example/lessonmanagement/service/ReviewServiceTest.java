package com.example.lessonmanagement.service;

import com.example.lessonmanagement.model.Review;
import com.example.lessonmanagement.model.Booking;
import com.example.lessonmanagement.repository.ReviewRepository;
import com.example.lessonmanagement.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReviewServiceTest {
    private ReviewService reviewService;
    private ReviewRepository reviewRepository;
    private BookingRepository bookingRepository;

    @BeforeEach
    void setUp() {
        reviewRepository = ReviewRepository.getInstance();
        bookingRepository = BookingRepository.getInstance();

        // Reset data before each test
        reviewRepository.clear();
        bookingRepository.clear();

        reviewService = new ReviewService(reviewRepository, bookingRepository);
    }

    @Test
    void testAddReview_Successful() {
        // Arrange - Create a booking before the review
        bookingRepository.addBooking(new Booking("Alice", "Lesson1", "Tutor1"));

        // Act - Create a valid review
        Review review = new Review("Alice", "Tutor1", 5, "Great lesson!");
        reviewService.addReview(review);

        // Assert - Check that the review has been saved correctly
        List<Review> reviews = reviewRepository.getReviewsByTutor("Tutor1");

        assertEquals(1, reviews.size());
        assertEquals("Alice", reviews.get(0).getStudentName());
        assertEquals(5, reviews.get(0).getRating());
        assertEquals("Great lesson!", reviews.get(0).getComment());
    }

    @Test
    void testAddReview_AlreadyReviewed() {
        // Arrange - Create a booking and an initial review
        bookingRepository.addBooking(new Booking("Alice", "Lesson1", "Tutor1"));
        reviewService.addReview(new Review("Alice", "Tutor1", 4, "Good lesson!"));

        // Act - Attempt to add a second review from the same student
        reviewService.addReview(new Review("Alice", "Tutor1", 5, "Even better now!"));

        // Assert - The second review MUST NOT be added
        List<Review> reviews = reviewRepository.getReviewsByTutor("Tutor1");

        assertEquals(1, reviews.size());  // Deve esserci solo la prima recensione
        assertEquals("Good lesson!", reviews.get(0).getComment());  // Confermiamo il contenuto
    }

    @Test
    void testAddReview_WithoutBooking() {
        // Arrange - No booking created
        Review review = new Review("Bob", "Tutor2", 3, "Not bad");

        // Act - Attempt to add a review without having booked a lesson
        reviewService.addReview(review);

        // Assert - No review should have been saved
        List<Review> reviews = reviewRepository.getReviewsByTutor("Tutor2");
        assertTrue(reviews.isEmpty());
    }

    @Test
    void testViewTutorProfile_WithReviews() {
        // Arrange - Create test bookings and reviews
        bookingRepository.addBooking(new Booking("Alice", "Lesson1", "Tutor1"));
        bookingRepository.addBooking(new Booking("Bob", "Lesson2", "Tutor1"));

        reviewService.addReview(new Review("Alice", "Tutor1", 5, "Excellent"));
        reviewService.addReview(new Review("Bob", "Tutor1", 4, "Very good"));

        // Act - Retrieve reviews for the tutor
        List<Review> reviews = reviewRepository.getReviewsByTutor("Tutor1");

        // Assert - Check that the reviews are present
        assertEquals(2, reviews.size());
        assertEquals("Excellent", reviews.get(0).getComment());
        assertEquals("Very good", reviews.get(1).getComment());
    }

    @Test
    void testViewTutorProfile_NoReviews() {
        // Arrange - No reviews for this tutor

        // Act - Retrieve reviews for a tutor with no reviews
        List<Review> reviews = reviewRepository.getReviewsByTutor("TutorX");

        // Assert - The list of reviews must be empty
        assertTrue(reviews.isEmpty());
    }
}
