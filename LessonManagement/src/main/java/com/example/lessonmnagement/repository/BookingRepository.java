package com.example.lessonmanagement.repository;

import com.example.lessonmanagement.model.Booking;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private static BookingRepository instance;
    private final List<Booking> bookings = new ArrayList<>();

    private BookingRepository() {}

    public static BookingRepository getInstance() {
        if (instance == null) {
            instance = new BookingRepository();
        }
        return instance;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(String studentName, String lessonTitle) {
        bookings.removeIf(booking -> booking.getStudentName().equals(studentName) && booking.getLessonTitle().equals(lessonTitle));
    }

    public boolean isLessonBooked(String lessonTitle) {
        return bookings.stream().anyMatch(booking -> booking.getLessonTitle().equals(lessonTitle));
    }

    public List<Booking> getBookings() {
        return bookings;
    }
}