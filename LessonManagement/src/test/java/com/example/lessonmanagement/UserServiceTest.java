package com.example.lessonmanagement.test;

import com.example.lessonmanagement.model.User;
import com.example.lessonmanagement.repository.UserRepository;
import com.example.lessonmanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);
    }

    @Test
    void testAddUser() {
        userService.addUser("Alice", "tutor");
        assertEquals(1, userRepository.getUsers().size());
        assertEquals("Alice", userRepository.getUsers().get(0).getName());
    }

    @Test
    void testIsTutor() {
        userService.addUser("Bob", "tutor");
        assertTrue(userService.isTutor("Bob"));
        assertFalse(userService.isTutor("Charlie"));
    }
}