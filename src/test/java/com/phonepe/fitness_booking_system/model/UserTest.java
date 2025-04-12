
package com.phonepe.fitness_booking_system.model;

import com.phonepe.fitness_booking_system.utils.UserTier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User platinumUser;
    private User silverUser;

    @BeforeEach
    public void setUp() {
        platinumUser = new User("john", "pass123", UserTier.PLATINUM);
        silverUser = new User("alice", "pass456", UserTier.SILVER);
    }

    @Test
    public void testUserCanBookInitially() {
        assertTrue(platinumUser.canBook());
        assertTrue(silverUser.canBook());
    }

    @Test
    public void testBookingLimitsEnforced() {
        for (int i = 0; i < UserTier.SILVER.getBookingLimit(); i++) {
            silverUser.bookClass(i);
        }

        assertFalse(silverUser.canBook());
    }

    @Test
    public void testBookingAndCancellationUpdatesCorrectly() {
        silverUser.bookClass(1);
        assertEquals(1, silverUser.getBookedClassIds().size());

        silverUser.cancelClass(1);
        assertEquals(0, silverUser.getBookedClassIds().size());
    }

    @Test
    public void testSetters() {
        silverUser.setBookedClassIds(null);
        silverUser.setId(1);
        silverUser.setPassword("newPassword");
        silverUser.setUsername("UserName");
        silverUser.setTier(UserTier.GOLD);
    }
}
