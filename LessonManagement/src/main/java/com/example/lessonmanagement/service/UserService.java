package com.example.lessonmanagement.service;

import com.example.lessonmanagement.model.User;
import com.example.lessonmanagement.repository.UserRepository;
import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean addUser(User user) {
        String trimmedName = user.getName().trim();

        boolean userExists = userRepository.getUsers().stream()
                .anyMatch(existingUser -> existingUser.getName().trim().equals(trimmedName));

        if (userExists) {
            System.out.println("Error: A user with the name \"" + user.getName() + "\" already exists.");
            return false;
        } else {
            userRepository.addUser(user);
            System.out.println("User registered: " + user.getName() + " as " + user.getRole());
            return true;
        }
    }





    public void viewUsers() {
        userRepository.getUsers().forEach(System.out::println);
    }

    public boolean isTutor(String name) {
        return userRepository.getUsers().stream().anyMatch(user -> user.getName().equalsIgnoreCase(name) && user.getRole().equals("tutor"));
    }

    public List<User> getTutorsByStudyField(String studyField) {
        return userRepository.getTutorsByStudyField(studyField);
    }
}