package com.example.lessonmanagement.factory;

import com.example.lessonmanagement.model.Booking;

public class BookingFactory {
    public static Booking createBooking(String studentName, String lessonTitle, String tutorName) {
        return new Booking(studentName, lessonTitle, tutorName);
    }
}