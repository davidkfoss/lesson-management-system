package com.example.lessonmanagement.service;

import com.example.lessonmanagement.model.Lesson;
import com.example.lessonmanagement.model.Booking;
import com.example.lessonmanagement.repository.LessonRepository;
import com.example.lessonmanagement.repository.BookingRepository;

import java.util.List;
import java.util.Scanner;

public class LessonService {
    private final LessonRepository lessonRepository;
    private final BookingRepository bookingRepository = BookingRepository.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public void addLesson(Lesson lesson) {
        lessonRepository.addLesson(lesson);
    }

    public void viewLessons() {
        lessonRepository.getLessons().forEach(System.out::println);
    }

    public void bookLesson(String studentName) {
        if (lessonRepository.getLessons().isEmpty()) {
            System.out.println("No available lessons to book.");
            return;
        }

        System.out.println("Available lessons:");
        int index = 1;
        for (Lesson lesson : lessonRepository.getLessons()) {
            System.out.println(index + ". " + lesson.getTitle() + " by " + lesson.getTutorName());
            index++;
        }

        System.out.print("Enter the number of the lesson you want to book: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > lessonRepository.getLessons().size()) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }

        Lesson selectedLesson = lessonRepository.getLessons().get(choice - 1);
        if (!bookingRepository.isLessonBooked(selectedLesson.getTitle())) {
            bookingRepository.addBooking(new Booking(studentName, selectedLesson.getTitle(), selectedLesson.getTutorName()));
            System.out.println("Lesson booked successfully!");
        } else {
            System.out.println("This lesson is already booked.");
        }
    }

    public void cancelLesson(String studentName) {
        List<Booking> studentBookings = bookingRepository.getBookings().stream()
                .filter(booking -> booking.getStudentName().equals(studentName))
                .toList();

        if (studentBookings.isEmpty()) {
            System.out.println("No bookings found for student: " + studentName);
            return;
        }

        System.out.println("Your booked lessons:");
        for (int i = 0; i < studentBookings.size(); i++) {
            System.out.println((i + 1) + ". " + studentBookings.get(i).getLessonTitle());
        }

        System.out.print("Enter the number of the lesson you want to cancel: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > studentBookings.size()) {
            System.out.println("Invalid selection. Please try again.");
            return;
        }

        Booking toRemove = studentBookings.get(choice - 1);
        bookingRepository.removeBooking(toRemove.getStudentName(), toRemove.getLessonTitle());
        System.out.println("Lesson canceled successfully.");
    }
}