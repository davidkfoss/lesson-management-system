package com.example.lessonmanagement.client;

import com.example.lessonmanagement.service.LessonService;
import com.example.lessonmanagement.service.UserService;
import com.example.lessonmanagement.model.User;
import com.example.lessonmanagement.model.Lesson;
import com.example.lessonmanagement.repository.UserRepository;
import com.example.lessonmanagement.repository.LessonRepository;
import com.example.lessonmanagement.factory.UserFactory;
import com.example.lessonmanagement.factory.LessonFactory;

import java.util.Scanner;

public class ConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserRepository userRepository = UserRepository.getInstance();
    private static final LessonRepository lessonRepository = LessonRepository.getInstance();
    private static final UserService userService = new UserService(userRepository);
    private static final LessonService lessonService = new LessonService(lessonRepository);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Online Lesson Management System ---");
            System.out.println("1. Register as a user");
            System.out.println("2. View registered users");
            System.out.println("3. Create a lesson (Tutor only)");
            System.out.println("4. View available lessons");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    userService.viewUsers();
                    break;
                case 3:
                    createLesson();
                    break;
                case 4:
                    lessonService.viewLessons();
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 5);
    }

    private static void registerUser() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Are you a tutor or a student? (tutor/student): ");
        String role = scanner.nextLine().toLowerCase();
        User user = UserFactory.createUser(name, role);
        userService.addUser(user);
    }

    private static void createLesson() {
        System.out.print("Enter tutor name: ");
        String tutorName = scanner.nextLine();
        if (!userService.isTutor(tutorName)) {
            System.out.println("Only registered tutors can create lessons.");
            return;
        }
        System.out.print("Enter lesson title: ");
        String title = scanner.nextLine();
        System.out.print("Enter lesson price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        Lesson lesson = LessonFactory.createLesson(tutorName, title, price);
        lessonService.addLesson(lesson);
    }
}