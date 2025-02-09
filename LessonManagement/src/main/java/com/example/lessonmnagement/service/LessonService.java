package com.example.lessonmanagement.service;

import com.example.lessonmanagement.model.Lesson;
import com.example.lessonmanagement.repository.LessonRepository;

public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public void addLesson(String tutorName, String title, double price) {
        lessonRepository.addLesson(new Lesson(tutorName, title, price));
        System.out.println("Lesson created: " + title + " by " + tutorName);
    }

    public void viewLessons() {
        lessonRepository.getLessons().forEach(System.out::println);
    }
}