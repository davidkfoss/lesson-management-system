package com.example.lessonmanagement.factory;

import com.example.lessonmanagement.model.User;
import java.util.List;

public class UserFactory {
    public static User createUser(String name, String role, List<String> studyFields) {
        return new User(name, role, studyFields);
    }
}