package com.example.lessonmanagement.service;

import com.example.lessonmanagement.model.User;
import com.example.lessonmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = UserRepository.getInstance(); // Singleton
        userService = new UserService(userRepository);
    }

    @Test
    void testAddUser() {
        User user = new User("Alice", "student", List.of());
        userService.addUser(user);

        List<User> users = userRepository.getUsers();
        assertEquals(1, users.size());
        assertEquals("Alice", users.get(0).getName());
        assertEquals("student", users.get(0).getRole());
        assertTrue(users.get(0).getStudyFields().isEmpty());
    }

    @Test
    void testIsTutor() {
        User tutor = new User("Bob", "tutor", List.of("Mathematics", "Physics")); // Con campi di studio
        userService.addUser(tutor);

        assertTrue(userService.isTutor("Bob"));
        assertFalse(userService.isTutor("Alice"));
    }

    @Test
    void testGetTutorsByStudyField() {
        User tutor1 = new User("Charlie", "tutor", List.of("Mathematics"));
        User tutor2 = new User("David", "tutor", List.of("Physics"));
        User student = new User("Eve", "student", List.of());

        userService.addUser(tutor1);
        userService.addUser(tutor2);
        userService.addUser(student);

        List<User> mathTutors = userService.getTutorsByStudyField("Mathematics");
        List<User> physicsTutors = userService.getTutorsByStudyField("Physics");
        List<User> chemistryTutors = userService.getTutorsByStudyField("Chemistry");

        assertEquals(1, mathTutors.size());
        assertEquals("Charlie", mathTutors.get(0).getName());

        assertEquals(1, physicsTutors.size());
        assertEquals("David", physicsTutors.get(0).getName());

        assertTrue(chemistryTutors.isEmpty());
    }
}
