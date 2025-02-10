
package com.example.lessonmanagement.factory;

import com.example.lessonmanagement.model.Lesson;
import com.example.lessonmanagement.strategy.PricingStrategy;

public class LessonFactory {
    public static Lesson createLesson(String tutorName, String title, double price, PricingStrategy pricingStrategy) {
        return new Lesson(tutorName, title, price, pricingStrategy);
    }
}