package com.example.lessonmanagement.strategy;

public class DiscountPricing implements PricingStrategy {
    private final double discountRate;

    public DiscountPricing(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * (1 - discountRate);
    }
}