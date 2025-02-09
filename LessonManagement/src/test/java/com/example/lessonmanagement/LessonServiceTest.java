package com.example.lessonmanagement.test;

import com.example.lessonmanagement.model.Lesson;
import com.example.lessonmanagement.repository.LessonRepository;
import com.example.lessonmanagement.service.LessonService;
import com.example.lessonmanagement.factory.LessonFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LessonServiceTest {
    private LessonService lessonService;
    private LessonRepository lessonRepository;

    @BeforeEach
    void setUp() {
        lessonRepository = LessonRepository.getInstance();
        lessonService = new LessonService(lessonRepository);

        // Reset the repository before each test to avoid interference
        lessonRepository.getLessons().clear();
    }

    @Test
    void testAddLesson() {
        Lesson lesson = LessonFactory.createLesson("John", "Math 101", 50.0);
        lessonService.addLesson(lesson);

        assertEquals(1, lessonRepository.getLessons().size());
        assertEquals("Math 101", lessonRepository.getLessons().get(0).getTitle());
    }

    @Test
    void testViewLessons() {
        lessonService.addLesson(LessonFactory.createLesson("Alice", "Physics", 75.0));
        lessonService.addLesson(LessonFactory.createLesson("Bob", "Chemistry", 65.0));

        assertEquals(2, lessonRepository.getLessons().size());
    }
}