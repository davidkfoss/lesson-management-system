package com.example.lessonmanagement.repository;

import com.example.lessonmanagement.model.Lesson;
import java.util.ArrayList;
import java.util.List;

public class LessonRepository {
    private static LessonRepository instance;
    private final List<Lesson> lessons = new ArrayList<>();

    private LessonRepository() {}

    public static LessonRepository getInstance() {
        if (instance == null) {
            instance = new LessonRepository();
        }
        return instance;
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public void clear() {
        lessons.clear();
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public List<Lesson> getLessonsByTutor(String tutorName) {
        return lessons.stream()
                .filter(l -> l.getTutorName().equals(tutorName))
                .collect(java.util.stream.Collectors.toList());
    }
}