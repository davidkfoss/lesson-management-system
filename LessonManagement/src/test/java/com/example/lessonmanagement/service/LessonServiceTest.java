package com.example.lessonmanagement.service;

import com.example.lessonmanagement.model.CustomizationRequest;
import com.example.lessonmanagement.model.LessonPackage;
import com.example.lessonmanagement.model.User;
import com.example.lessonmanagement.repository.BookingRepository;
import com.example.lessonmanagement.repository.CustomizationRequestRepository;
import com.example.lessonmanagement.repository.LessonPackageRepository;
import com.example.lessonmanagement.repository.LessonRepository;
import com.example.lessonmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LessonServiceTest {
    private LessonService lessonService;
    private LessonRepository lessonRepository;
    private BookingRepository bookingRepository;
    private CustomizationRequestRepository customizationRequestRepository;
    private UserService userService;
    private final InputStream originalSystemIn = System.in;

    @BeforeEach
    void setUp() {
        lessonRepository = LessonRepository.getInstance();
        bookingRepository = BookingRepository.getInstance();
        customizationRequestRepository = CustomizationRequestRepository.getInstance();
        userService = new UserService(UserRepository.getInstance());
        lessonRepository.clear();
        bookingRepository.clear();
        customizationRequestRepository.clearRequestsByTutor("Any");

        String simulatedInput = "1\n";
        InputService inputService = new InputService(new Scanner(new ByteArrayInputStream(simulatedInput.getBytes())));
        lessonService = new LessonService(lessonRepository, bookingRepository, inputService, userService);
    }

    @Test
    void testSuccessfulStudentRegistration() {
        User student = new User("Vittorio", "student", List.of());
        assertTrue(userService.addUser(student));
    }

    @Test
    void testDuplicateUserRegistrationFails() {
        User user1 = new User("Alice", "student", List.of());
        User user2 = new User("Alice", "student", List.of());
        userService.addUser(user1);
        assertFalse(userService.addUser(user2));
    }



    @Test
    void testRequestLessonCustomization_Success() {
        String studentName = "Alice";
        String tutorName = "Dr. Brown";
        String customizationDetails = "Vorrei più esercizi pratici";
        CustomizationRequest request = new CustomizationRequest(studentName, tutorName, customizationDetails);
        customizationRequestRepository.addRequest(request);
        List<CustomizationRequest> requests = customizationRequestRepository.getRequestsByTutor(tutorName);
        assertEquals(1, requests.size());
        assertEquals(studentName, requests.get(0).getStudentName());
        assertEquals(tutorName, requests.get(0).getTutorName());
        assertEquals(customizationDetails, requests.get(0).getRequestDetails());
    }

    @Test
    void testPurchaseLessonPackage_Success() {
        String studentName = "Alice";
        String tutorName = "Dr. Brown";
        String packageTitle = "Physics Masterclass";
        double packagePrice = 150.0;
        LessonPackage lessonPackage = new LessonPackage(tutorName, packageTitle, packagePrice);
        LessonPackageRepository.getInstance().addPackage(lessonPackage);

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        try {
            lessonService.purchaseLessonPackage(studentName, tutorName);
        } finally {
            System.setIn(originalSystemIn);
        }

        List<LessonPackage> packages = LessonPackageRepository.getInstance().getPackagesByTutor(tutorName);
        assertEquals(1, packages.size());
        assertEquals(packageTitle, packages.get(0).getTitle());
        assertEquals(packagePrice, packages.get(0).getPrice());
    }

    @Test
    void testManageCustomizationRequests_Success() {
        String tutorName = "Dr. Brown";
        String studentName = "Alice";
        String customizationDetails = "I would like a more interactive lesson.";
        customizationRequestRepository.clearRequestsByTutor(tutorName);
        customizationRequestRepository.addRequest(new CustomizationRequest(studentName, tutorName, customizationDetails));

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        try {
            lessonService.manageCustomizationRequests(tutorName);
        } finally {
            System.setIn(originalSystemIn);
        }

        List<CustomizationRequest> requests = customizationRequestRepository.getRequestsByTutor(tutorName);
        assertEquals(0, requests.size(), "The number of requests after handling is not correct.");
    }



}
