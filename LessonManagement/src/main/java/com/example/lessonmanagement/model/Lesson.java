package com.example.lessonmanagement.model;

import com.example.lessonmanagement.strategy.PricingStrategy;

public class Lesson {
    private String tutorName;
    private String title;
    private double basePrice;
    private PricingStrategy pricingStrategy;

    public Lesson(String tutorName, String title, double basePrice, PricingStrategy pricingStrategy) {
        this.tutorName = tutorName;
        this.title = title;
        this.basePrice = basePrice;
        this.pricingStrategy = pricingStrategy;
    }

    public String getTutorName() {
        return tutorName;
    }

    public String getTitle() {
        return title;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getFinalPrice() {
        return pricingStrategy.calculatePrice(basePrice);
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "tutorName='" + tutorName + '\'' +
                ", title='" + title + '\'' +
                ", basePrice=" + basePrice +
                ", finalPrice=" + getFinalPrice() +
                '}';
    }
}
