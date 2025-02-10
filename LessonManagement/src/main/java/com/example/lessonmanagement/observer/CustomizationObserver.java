package com.example.lessonmanagement.observer;

public interface CustomizationObserver {
    void notifyCustomizationRequest(String tutorName, String studentName, String requestDetails);
}