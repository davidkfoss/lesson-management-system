package com.example.lessonmanagement.service;

import com.example.lessonmanagement.model.User;
import com.example.lessonmanagement.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        userRepository.addUser(user);
        System.out.println("User registered: " + user.getName() + " as " + user.getRole());
    }

    public void viewUsers() {
        userRepository.getUsers().forEach(System.out::println);
    }

    public boolean isTutor(String name) {
        return userRepository.getUsers().stream().anyMatch(user -> user.getName().equalsIgnoreCase(name) && user.getRole().equals("tutor"));
    }
}