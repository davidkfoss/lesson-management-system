package com.example.lessonmanagement.client;

import com.example.lessonmanagement.service.LessonService;
import com.example.lessonmanagement.service.UserService;
import com.example.lessonmanagement.service.ReviewService;
import com.example.lessonmanagement.model.User;
import com.example.lessonmanagement.model.Lesson;
import com.example.lessonmanagement.model.Review;
import com.example.lessonmanagement.repository.UserRepository;
import com.example.lessonmanagement.repository.LessonRepository;
import com.example.lessonmanagement.repository.BookingRepository;
import com.example.lessonmanagement.repository.ReviewRepository;
import com.example.lessonmanagement.factory.UserFactory;
import com.example.lessonmanagement.factory.LessonFactory;
import com.example.lessonmanagement.factory.ReviewFactory;

import java.util.Scanner;

public class ConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserRepository userRepository = UserRepository.getInstance();
    private static final LessonRepository lessonRepository = LessonRepository.getInstance();
    private static final BookingRepository bookingRepository = BookingRepository.getInstance();
    private static final ReviewRepository reviewRepository = ReviewRepository.getInstance();
    private static final UserService userService = new UserService(userRepository);
    private static final LessonService lessonService = new LessonService(lessonRepository);
    private static final ReviewService reviewService = new ReviewService(reviewRepository, bookingRepository);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Online Lesson Management System ---");
            System.out.println("1. Register as a user");
            System.out.println("2. View registered users");
            System.out.println("3. Create a lesson (Tutor only)");
            System.out.println("4. View available lessons");
            System.out.println("5. Book a lesson (Student only)");
            System.out.println("6. Cancel a lesson");
            System.out.println("7. Review a tutor");
            System.out.println("8. View tutor profiles");
            System.out.println("9. Exit");
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
                    bookLesson();
                    break;
                case 6:
                    cancelLesson();
                    break;
                case 7:
                    reviewTutor();
                    break;
                case 8:
                    viewTutorProfile();
                    break;
                case 9:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 9);
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

    private static void bookLesson() {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        lessonService.bookLesson(studentName);
    }

    private static void cancelLesson() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        lessonService.cancelLesson(name);
    }

    private static void reviewTutor() {
        System.out.print("Enter your name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter tutor's name: ");
        String tutorName = scanner.nextLine();
        System.out.print("Enter rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your review: ");
        String comment = scanner.nextLine();
        Review review = ReviewFactory.createReview(studentName, tutorName, rating, comment);
        reviewService.addReview(review);
    }

    private static void viewTutorProfile() {
        System.out.print("Enter tutor's name: ");
        String tutorName = scanner.nextLine();
        reviewService.viewTutorProfile(tutorName);
    }
}