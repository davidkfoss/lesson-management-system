package com.example.lessonmanagement.repository;

import com.example.lessonmanagement.model.CustomizationRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomizationRequestRepository {
    private static CustomizationRequestRepository instance;
    private final List<CustomizationRequest> requests = new ArrayList<>();

    private CustomizationRequestRepository() {}

    public static CustomizationRequestRepository getInstance() {
        if (instance == null) {
            instance = new CustomizationRequestRepository();
        }
        return instance;
    }

    public void addRequest(CustomizationRequest request) {
        requests.add(request);
    }

    public List<CustomizationRequest> getRequestsByTutor(String tutorName) {
        return requests.stream()
                .filter(r -> r.getTutorName().equals(tutorName))
                .collect(Collectors.toList());
    }

    public void approveRequest(String tutorName, String studentName) {
        requests.stream()
                .filter(req -> req.getTutorName().equalsIgnoreCase(tutorName) && req.getStudentName().equalsIgnoreCase(studentName))
                .findFirst()
                .ifPresent(CustomizationRequest::approveRequest);
    }
}