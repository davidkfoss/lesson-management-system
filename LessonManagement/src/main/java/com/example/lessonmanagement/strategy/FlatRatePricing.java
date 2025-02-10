package com.example.lessonmanagement.strategy;

public class FlatRatePricing implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice;
    }
}