package com.example.lessonmanagement.strategy;

public class DynamicDemandPricing implements PricingStrategy {
    private final double demandFactor;

    public DynamicDemandPricing(double demandFactor) {
        this.demandFactor = demandFactor;
    }

    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * demandFactor;
    }
}