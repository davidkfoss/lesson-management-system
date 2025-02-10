package com.example.lessonmanagement.repository;

import com.example.lessonmanagement.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {
    private static UserRepository instance;
    private final List<User> users = new ArrayList<>();

    private UserRepository() {}

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<User> getTutorsByStudyField(String studyField) {
        return users.stream()
                .filter(user -> "Tutor".equalsIgnoreCase(user.getRole()) && user.getStudyFields().contains(studyField))
                .collect(Collectors.toList());
    }
}