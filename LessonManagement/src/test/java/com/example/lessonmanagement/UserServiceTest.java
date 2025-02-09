package com.example.lessonmanagement.test;

import com.example.lessonmanagement.model.User;
import com.example.lessonmanagement.repository.UserRepository;
import com.example.lessonmanagement.service.UserService;
import com.example.lessonmanagement.factory.UserFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = UserRepository.getInstance();
        userService = new UserService(userRepository);

        // Reset the repository to avoid test data from previous runs
        userRepository.getUsers().clear();
    }

    @Test
    void testAddUser() {
        User user = UserFactory.createUser("Alice", "tutor");
        userService.addUser(user);

        assertEquals(1, userRepository.getUsers().size());
        assertEquals("Alice", userRepository.getUsers().get(0).getName());
    }

    @Test
    void testIsTutor() {
        User tutor = UserFactory.createUser("Bob", "tutor");
        userService.addUser(tutor);

        assertTrue(userService.isTutor("Bob"));
        assertFalse(userService.isTutor("Charlie"));
    }
}