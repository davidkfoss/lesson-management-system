package com.example.lessonmanagement.repository;

import com.example.lessonmanagement.model.User;
import java.util.ArrayList;
import java.util.List;

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
}
