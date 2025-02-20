package com.example.lessonmanagement.repository;

import com.example.lessonmanagement.model.CustomizationRequest;
import com.example.lessonmanagement.observer.CustomizationObserver;

import java.util.ArrayList;
import java.util.List;

public class CustomizationRequestRepository {
    private static final CustomizationRequestRepository instance = new CustomizationRequestRepository();
    private final List<CustomizationRequest> customizationRequests = new ArrayList<>();
    private final List<CustomizationObserver> observers = new ArrayList<>();

    private CustomizationRequestRepository() {}

    public static CustomizationRequestRepository getInstance() {
        return instance;
    }

    public void addRequest(CustomizationRequest request) {
        customizationRequests.add(request);
        notifyObservers(request);
    }

    public List<CustomizationRequest> getRequestsByTutor(String tutorName) {
        List<CustomizationRequest> tutorRequests = new ArrayList<>();
        for (CustomizationRequest request : customizationRequests) {
            if (request.getTutorName().equalsIgnoreCase(tutorName)) {
                tutorRequests.add(request);
            }
        }
        return tutorRequests;
    }

    public void approveRequest(String tutorName, String studentName) {
        customizationRequests.removeIf(request ->
                request.getTutorName().equalsIgnoreCase(tutorName) &&
                        request.getStudentName().equalsIgnoreCase(studentName));
        System.out.println("Customization request approved and removed.");
    }

    public void addObserver(CustomizationObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(CustomizationRequest request) {
        for (CustomizationObserver observer : observers) {
            observer.notifyCustomizationRequest(request.getTutorName(), request.getStudentName(), request.getRequestDetails());
        }
    }

    public void clearRequestsByTutor(String tutorName) {
        customizationRequests.removeIf(request -> request.getTutorName().equalsIgnoreCase(tutorName));
    }

}