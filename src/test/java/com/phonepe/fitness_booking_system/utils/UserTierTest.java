package com.phonepe.fitness_booking_system.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTierTest {

    @Test
    void testBookingLimits() {
        assertEquals(10, UserTier.PLATINUM.getBookingLimit());
        assertEquals(5, UserTier.GOLD.getBookingLimit());
        assertEquals(3, UserTier.SILVER.getBookingLimit());
    }

    @Test
    void testEnumValues() {
        assertNotNull(UserTier.valueOf("PLATINUM"));
        assertNotNull(UserTier.valueOf("GOLD"));
        assertNotNull(UserTier.valueOf("SILVER"));
    }

}
