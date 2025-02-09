package com.example.lessonmanagement.service;

import com.example.lessonmanagement.model.Lesson;
import com.example.lessonmanagement.repository.LessonRepository;

public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public void addLesson(Lesson lesson) {
        lessonRepository.addLesson(lesson);
        System.out.println("Lesson created: " + lesson.getTitle() + " by " + lesson.getTutorName());
    }

    public void viewLessons() {
        lessonRepository.getLessons().forEach(System.out::println);
    }
}