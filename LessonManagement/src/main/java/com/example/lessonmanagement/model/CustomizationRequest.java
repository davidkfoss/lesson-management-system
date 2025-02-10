package com.example.lessonmanagement.model;

public class CustomizationRequest {
    private String studentName;
    private String tutorName;
    private String requestDetails;
    private boolean approved;

    public CustomizationRequest(String studentName, String tutorName, String requestDetails) {
        this.studentName = studentName;
        this.tutorName = tutorName;
        this.requestDetails = requestDetails;
        this.approved = false;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getTutorName() {
        return tutorName;
    }

    public String getRequestDetails() {
        return requestDetails;
    }

    public boolean isApproved() {
        return approved;
    }

    public void approveRequest() {
        this.approved = true;
    }

    @Override
    public String toString() {
        return "CustomizationRequest{" +
                "studentName='" + studentName + '\'' +
                ", tutorName='" + tutorName + '\'' +
                ", requestDetails='" + requestDetails + '\'' +
                ", approved=" + approved +
                '}';
    }
}
