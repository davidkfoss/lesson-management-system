
package com.example.lessonmanagement.model;

public class Lesson {
    private String tutorName;
    private String title;
    private double price;

    public Lesson(String tutorName, String title, double price) {
        this.tutorName = tutorName;
        this.title = title;
        this.price = price;
    }

    public String getTutorName() { return tutorName; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return title + " by " + tutorName + " ($" + price + ")";
    }
}
