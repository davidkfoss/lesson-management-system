package com.example.lessonmanagement.observer;

import com.example.lessonmanagement.repository.NotificationRepository;

public class TutorNotificationService implements CustomizationObserver {
    private final NotificationRepository notificationRepository = NotificationRepository.getInstance();

    @Override
    public void notifyCustomizationRequest(String tutorName, String studentName, String requestDetails) {
        String message = "New customization request from " + studentName + ": " + requestDetails;
        notificationRepository.addNotification(tutorName, message);
    }
}