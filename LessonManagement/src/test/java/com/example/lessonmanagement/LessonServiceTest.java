// package com.example.lessonmanagement.test;

// import com.example.lessonmanagement.model.Lesson;
// import com.example.lessonmanagement.repository.LessonRepository;
// import com.example.lessonmanagement.repository.BookingRepository;
// import com.example.lessonmanagement.service.LessonService;
// import com.example.lessonmanagement.factory.LessonFactory;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import static org.junit.jupiter.api.Assertions.*;

// class LessonServiceTest {
//     private LessonService lessonService;
//     private LessonRepository lessonRepository;
//     private BookingRepository bookingRepository;

//     @BeforeEach
//     void setUp() {
//         lessonRepository = LessonRepository.getInstance();
//         bookingRepository = BookingRepository.getInstance();
//         lessonService = new LessonService(lessonRepository);

//         // Reset repositories before each test to avoid interference
//         lessonRepository.getLessons().clear();
//         bookingRepository.getInstance().getClass();
//     }

//     @Test
//     void testAddLesson() {
//         Lesson lesson = LessonFactory.createLesson("John", "Math 101", 50.0);
//         lessonService.addLesson(lesson);

//         assertEquals(1, lessonRepository.getLessons().size());
//         assertEquals("Math 101", lessonRepository.getLessons().get(0).getTitle());
//     }

//     @Test
//     void testViewLessons() {
//         lessonService.addLesson(LessonFactory.createLesson("Alice", "Physics", 75.0));
//         lessonService.addLesson(LessonFactory.createLesson("Bob", "Chemistry", 65.0));

//         assertEquals(2, lessonRepository.getLessons().size());
//     }

//     @Test
//     void testBookLesson() {
//         Lesson lesson = LessonFactory.createLesson("Tutor1", "Math", 50.0);
//         lessonService.addLesson(lesson);
//         lessonService.bookLesson("Student1");
//         assertTrue(bookingRepository.isLessonBooked("Math"));
//     }

//     @Test
//     void testCancelBooking() {
//         Lesson lesson = LessonFactory.createLesson("Tutor1", "Math", 50.0);
//         lessonService.addLesson(lesson);
//         lessonService.bookLesson("Student1");
//         lessonService.cancelLesson("Student1");
//         assertFalse(bookingRepository.isLessonBooked("Math"));
//     }
// }