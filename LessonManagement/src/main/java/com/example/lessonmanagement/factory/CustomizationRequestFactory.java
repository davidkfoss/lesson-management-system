package com.example.lessonmanagement.factory;

import com.example.lessonmanagement.model.CustomizationRequest;

class CustomizationRequestFactory {
    public static CustomizationRequest createCustomizationRequest(String studentName, String tutorName, String requestDetails) {
        return new CustomizationRequest(studentName, tutorName, requestDetails);
    }
}