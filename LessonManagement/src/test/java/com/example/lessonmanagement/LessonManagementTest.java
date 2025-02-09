// package com.example.lessonmanagement.test;

// import com.example.lessonmanagement.model.Booking;
// import com.example.lessonmanagement.model.Review;
// import com.example.lessonmanagement.repository.BookingRepository;
// import com.example.lessonmanagement.repository.ReviewRepository;
// import com.example.lessonmanagement.service.LessonService;
// import com.example.lessonmanagement.service.ReviewService;
// import com.example.lessonmanagement.repository.LessonRepository;
// import com.example.lessonmanagement.model.Lesson;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.*;

// import java.util.List;

// public class LessonManagementTest {
//     private LessonRepository lessonRepository;
//     private BookingRepository bookingRepository;
//     private ReviewRepository reviewRepository;
//     private LessonService lessonService;
//     private ReviewService reviewService;

//     @BeforeEach
//     void setUp() {
//         lessonRepository = LessonRepository.getInstance();
//         bookingRepository = BookingRepository.getInstance();
//         reviewRepository = ReviewRepository.getInstance();
//         lessonService = new LessonService(lessonRepository);
//         reviewService = new ReviewService(reviewRepository, bookingRepository);

//         // Clear repositories to prevent test interference
//         lessonRepository.getLessons().clear();
//         bookingRepository.getInstance().getClass();
//         reviewRepository.getInstance().getClass();
//     }

//     @Test
//     void testLessonCreation() {
//         Lesson lesson = new Lesson("Tutor1", "Math", 50.0);
//         lessonService.addLesson(lesson);
//         assertEquals(1, lessonRepository.getLessons().size());
//     }

//     @Test
//     void testBookingLesson() {
//         Lesson lesson = new Lesson("Tutor1", "Math", 50.0);
//         lessonService.addLesson(lesson);
//         lessonService.bookLesson("Student1");
//         assertTrue(bookingRepository.isLessonBooked("Math"));
//     }

//     @Test
//     void testCancelBooking() {
//         Lesson lesson = new Lesson("Tutor1", "Math", 50.0);
//         lessonService.addLesson(lesson);
//         lessonService.bookLesson("Student1");
//         lessonService.cancelLesson("Student1");

//         // Ensure booking is removed
//         assertFalse(bookingRepository.isLessonBooked("Math"));
//         assertEquals(0, bookingRepository.getBookings().size());
//     }
// }