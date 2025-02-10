
package com.example.lessonmanagement.factory;

import com.example.lessonmanagement.model.Lesson;

public class LessonFactory {
    public static Lesson createLesson(String tutorName, String title, double price) {
        return new Lesson(tutorName, title, price);
    }
}