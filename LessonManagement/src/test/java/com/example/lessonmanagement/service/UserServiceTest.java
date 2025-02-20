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
        userRepository.clear();
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
        User tutor = new User("Bob", "tutor", List.of("Mathematics", "Physics"));
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

        assertEquals(1, userService.getTutorsByStudyField("Mathematics").size());
        assertEquals(1, userService.getTutorsByStudyField("Physics").size());
        assertTrue(userService.getTutorsByStudyField("Chemistry").isEmpty());
    }

    @Test
    void testSuccessfulUserRegistration() {
        User user = new User("Alice", "student", List.of());
        boolean result = userService.addUser(user);
        assertTrue(result, "La registrazione dovrebbe avere successo.");
    }

    @Test
    void testDuplicateUserRegistrationFails() {
        User user1 = new User("Bob", "student", List.of());
        User user2 = new User(" Bob ", "student", List.of());

        assertTrue(userService.addUser(user1), "La prima registrazione deve avere successo.");
        assertFalse(userService.addUser(user2), "La seconda registrazione deve fallire a causa del nome duplicato.");
    }

    @Test
    void testCaseSensitiveUserNames() {
        User user1 = new User("Charlie", "student", List.of());
        User user2 = new User("charlie", "student", List.of());

        assertTrue(userService.addUser(user1), "La prima registrazione deve avere successo.");
        assertTrue(userService.addUser(user2), "La seconda registrazione deve avere successo se i nomi differiscono per maiuscole/minuscole.");
    }
}
