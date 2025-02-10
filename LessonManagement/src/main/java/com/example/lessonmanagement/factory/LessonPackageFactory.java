package com.example.lessonmanagement.factory;

import com.example.lessonmanagement.model.LessonPackage;

class LessonPackageFactory {
    public static LessonPackage createLessonPackage(String tutorName, String title, double price) {
        return new LessonPackage(tutorName, title, price);
    }
}