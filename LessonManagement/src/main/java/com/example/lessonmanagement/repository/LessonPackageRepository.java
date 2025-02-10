package com.example.lessonmanagement.repository;

import com.example.lessonmanagement.model.LessonPackage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LessonPackageRepository {
    private static LessonPackageRepository instance;
    private final List<LessonPackage> lessonPackages = new ArrayList<>();

    private LessonPackageRepository() {}

    public static LessonPackageRepository getInstance() {
        if (instance == null) {
            instance = new LessonPackageRepository();
        }
        return instance;
    }

    public void addPackage(LessonPackage lessonPackage) {
        lessonPackages.add(lessonPackage);
    }

    public List<LessonPackage> getPackagesByTutor(String tutorName) {
        return lessonPackages.stream()
                .filter(p -> p.getTutorName().equals(tutorName))
                .collect(Collectors.toList());
    }

    public void removePackage(String tutorName, String packageTitle) {
        lessonPackages.removeIf(p -> p.getTutorName().equals(tutorName) && p.getTitle().equals(packageTitle));
    }
}