package com.example.lessonmanagement.factory;

import com.example.lessonmanagement.model.User;

public class UserFactory {
    public static User createUser(String name, String role) {
        return new User(name, role);
    }
}