package com.example.lessonmanagement.client;

import com.example.lessonmanagement.model.User;
//import com.example.lessonmanagement.model.Lesson;
import com.example.lessonmanagement.service.LessonService;
import com.example.lessonmanagement.repository.LessonRepository;
import com.example.lessonmanagement.repository.UserRepository;
//import com.example.lessonmanagement.factory.LessonFactory;
import com.example.lessonmanagement.service.UserService;
//import com.example.lessonmanagement.observer.CustomizationObserver;
import com.example.lessonmanagement.observer.TutorNotificationService;
import com.example.lessonmanagement.repository.CustomizationRequestRepository;
import com.example.lessonmanagement.repository.NotificationRepository;
import com.example.lessonmanagement.repository.BookingRepository;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final LessonService lessonService = new LessonService(LessonRepository.getInstance(), BookingRepository.getInstance(), new Scanner(System.in));

    private static final UserService userService = new UserService(UserRepository.getInstance());
    private static final CustomizationRequestRepository customizationRequestRepository = CustomizationRequestRepository.getInstance();
    private static final TutorNotificationService tutorNotificationService = new TutorNotificationService();
    private static final NotificationRepository notificationRepository = NotificationRepository.getInstance();

    static {
        customizationRequestRepository.addObserver(tutorNotificationService);
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWelcome to the Lesson Management System");
            System.out.println("1. Student Menu");
            System.out.println("2. Tutor Menu");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                int mainChoice = scanner.nextInt();
                scanner.nextLine();

                switch (mainChoice) {
                    case 1:
                        studentMenu();
                        break;
                    case 2:
                        tutorMenu();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice, try again.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }


    private static void studentMenu() {
        while (true) {
            System.out.println("\nStudent Menu");
            System.out.println("1. Register as Student");
            System.out.println("2. Book a Lesson");
            System.out.println("3. Cancel a Lesson");
            System.out.println("4. View Lesson Calendar");
            System.out.println("5. Request Lesson Customization");
            System.out.println("6. View Lessons by Tutor");
            System.out.println("7. Find Tutors by Study Field");
            System.out.println("8. Purchase a Lesson Package");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        registerUser("student");
                        break;
                    case 2:
                        bookLesson();
                        break;
                    case 3:
                        cancelLesson();
                        break;
                    case 4:
                        viewLessonCalendar();
                        break;
                    case 5:
                        requestLessonCustomization();
                        break;
                    case 6:
                        viewLessonsByTutor();
                        break;
                    case 7:
                        findTutorsByStudyField();
                        break;
                    case 8:
                        purchaseLessonPackage();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice, try again.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }


    private static void tutorMenu() {
        while (true) {
            System.out.println("\nTutor Menu");
            System.out.println("1. Register as Tutor");
            System.out.println("2. Create a Lesson");
            System.out.println("3. Manage Lesson Packages");
            System.out.println("4. Manage Customization Requests");
            System.out.println("5. View signed up students");
            System.out.println("6. View Notifications");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        registerUser("tutor");
                        break;
                    case 2:
                        createLesson();
                        break;
                    case 3:
                        manageLessonPackages();
                        break;
                    case 4:
                        manageCustomizationRequests();
                        break;
                    case 5:
                        viewSignedUpStudents();
                        break;
                    case 6:
                        viewNotifications();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid option. Please select a valid choice.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void registerUser(String role) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();  // Applica trim all’input utente
        List<String> studyFields = null;

        if (role.equalsIgnoreCase("tutor")) {
            System.out.print("Enter study fields (comma separated): ");
            String fieldsInput = scanner.nextLine();
            studyFields = Arrays.asList(fieldsInput.split(","));
        }

        User user = new User(name, role, studyFields);
        boolean isRegistered = userService.addUser(user);  // Ritorna true o false

        if (isRegistered) {
            System.out.println(role + " registered successfully!");
        }
    }



    private static void bookLesson() {
        System.out.print("Enter your name: ");
        String studentName = scanner.nextLine();
        lessonService.bookLesson(studentName);
    }

    private static void createLesson() {
        lessonService.createLesson();
    }

    private static void cancelLesson() {
        System.out.print("Enter your name: ");
        String studentName = scanner.nextLine();
        lessonService.cancelLesson(studentName);
    }

    private static void viewLessonCalendar() {
        System.out.print("Enter your name: ");
        String studentName = scanner.nextLine();
        lessonService.viewLessonCalendar(studentName);
    }

    private static void requestLessonCustomization() {
        System.out.print("Enter your student name: ");
        String studentName = scanner.nextLine();
        lessonService.requestLessonCustomization(studentName);
    }

    private static void viewLessonsByTutor() {
        System.out.print("Enter tutor name: ");
        String tutorName = scanner.nextLine();
        lessonService.viewLessonsByTutor(tutorName);
    }

    private static void findTutorsByStudyField() {
        System.out.print("Enter study field: ");
        String studyField = scanner.nextLine();
        List<User> tutors = userService.getTutorsByStudyField(studyField);
        if (tutors.isEmpty()) {
            System.out.println("No tutors found for this study field.");
        } else {
            tutors.forEach(System.out::println);
        }
    }

    private static void purchaseLessonPackage() {
        System.out.print("Enter your name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter tutor name: ");
        String tutorName = scanner.nextLine();
        lessonService.purchaseLessonPackage(studentName, tutorName);
    }

    private static void manageLessonPackages() {
        System.out.print("Enter your tutor name: ");
        String tutorName = scanner.nextLine();
        lessonService.manageLessonPackages(tutorName);
    }

    private static void manageCustomizationRequests() {
        System.out.print("Enter your tutor name: ");
        String tutorName = scanner.nextLine();
        lessonService.manageCustomizationRequests(tutorName);
    }

    private static void viewSignedUpStudents() {
        System.out.print("Enter your tutor name: ");
        String tutorName = scanner.nextLine();
        lessonService.viewSignedUpStudents(tutorName);
    }

    private static void viewNotifications() {
        System.out.print("Enter your tutor name: ");
        String tutorName = scanner.nextLine();
        List<String> notifications = notificationRepository.getNotificationsForTutor(tutorName);
        if (notifications.isEmpty()) {
            System.out.println("No new notifications.");
        } else {
            System.out.println("Your Notifications:");
            notifications.forEach(System.out::println);
            notificationRepository.clearNotifications(tutorName);
            System.out.println("Notifications cleared.");
        }
    }

}
