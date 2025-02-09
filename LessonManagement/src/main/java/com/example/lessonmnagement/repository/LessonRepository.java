
package com.example.lessonmanagement.repository;

import com.example.lessonmanagement.model.Lesson;
import java.util.ArrayList;
import java.util.List;

public class LessonRepository {
    private final List<Lesson> lessons = new ArrayList<>();

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    public List<Lesson> getLessons() {
        return lessons;
    }
}


