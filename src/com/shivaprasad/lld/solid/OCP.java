package com.shivaprasad.lld.solid;

// Without OCP

class DiscountCalculator {
    public double calculateDiscount(String userType, double totalPrice) {
        if ("regular".equals(userType)) {
            return totalPrice * 0.1; // 10% discount
        } else if ("premium".equals(userType)) {
            return totalPrice * 0.2; // 20% discount
        }
        return 0;
    }
}


public class OCP {
    // Just main method code
}

// With OCP

interface Discount {
    double apply(double totalPrice);
}

class RegularDiscount implements Discount {
    @Override
    public double apply(double totalPrice) {
        return totalPrice * 0.1; // 10% discount
    }
}

class PremiumDiscount implements Discount {
    @Override
    public double apply(double totalPrice) {
        return totalPrice * 0.2; // 20% discount
    }
}

class DiscountCalculator1 {
    public double calculateDiscount(Discount discount, double totalPrice) {
        return discount.apply(totalPrice);
    }
}
