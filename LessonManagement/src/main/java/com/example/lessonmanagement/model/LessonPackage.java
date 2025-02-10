package com.example.lessonmanagement.model;

public class LessonPackage {
    private String tutorName;
    private String title;
    private double price;

    public LessonPackage(String tutorName, String title, double price) {
        this.tutorName = tutorName;
        this.title = title;
        this.price = price;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "LessonPackage{" +
                "tutorName='" + tutorName + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}