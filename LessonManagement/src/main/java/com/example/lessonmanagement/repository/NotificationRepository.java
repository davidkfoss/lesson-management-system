package com.example.lessonmanagement.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationRepository {
    private static final NotificationRepository instance = new NotificationRepository();
    private final Map<String, List<String>> tutorNotifications = new HashMap<>();

    private NotificationRepository() {}

    public static NotificationRepository getInstance() {
        return instance;
    }

    public void addNotification(String tutorName, String message) {
        tutorNotifications.computeIfAbsent(tutorName, k -> new ArrayList<>()).add(message);
    }

    public List<String> getNotificationsForTutor(String tutorName) {
        return tutorNotifications.getOrDefault(tutorName, new ArrayList<>());
    }

    public void clearNotifications(String tutorName) {
        tutorNotifications.remove(tutorName);
    }
}