package com.phonepe.fitness_booking_system.utils;

public enum UserTier {
    PLATINUM(10), GOLD(5), SILVER(3);

    int bookingLimit;

    UserTier(int limit) {
        this.bookingLimit = limit;
    }

    public int getBookingLimit() {
        return bookingLimit;
    }
}
