package com.phonepe.fitness_booking_system.service;

import com.phonepe.fitness_booking_system.model.FitnessClass;
import com.phonepe.fitness_booking_system.model.User;
import com.phonepe.fitness_booking_system.utils.ClassType;
import com.phonepe.fitness_booking_system.utils.UserTier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    private UserService userService;
    private ClassService classService;
    private BookingService bookingService;

    @BeforeEach
    void setup() {
        userService = new UserService();
        classService = new ClassService();
        bookingService = new BookingService(userService, classService);
    }

    @Test
    void bookClassSuccessfully() {
        User user = userService.register("mike", "123", UserTier.PLATINUM);
        FitnessClass fc = classService.createClass(ClassType.YOGA, 5, LocalDateTime.now().plusDays(1));

        bookingService.bookClass(user.getId(), fc.getId());

        assertTrue(user.getBookedClassIds().contains(fc.getId()));
        assertTrue(fc.getBookedUserIds().contains(user.getId()));
    }

    @Test
    void bookAlreadyBookedClassThrowsException() {
        User user = userService.register("anna", "123", UserTier.GOLD);
        FitnessClass fc = classService.createClass(ClassType.GYM, 3, LocalDateTime.now().plusDays(1));

        bookingService.bookClass(user.getId(), fc.getId());
        assertThrows(RuntimeException.class, () -> bookingService.bookClass(user.getId(), fc.getId()));
    }

    @Test
    void cancelBookingRemovesBookingAndMovesFromWaitlist() {
        User u1 = userService.register("user1", "p", UserTier.SILVER);
        User u2 = userService.register("user2", "p", UserTier.SILVER);
        FitnessClass fc = classService.createClass(ClassType.DANCE, 1, LocalDateTime.now().plusDays(1));

        bookingService.bookClass(u1.getId(), fc.getId());
        bookingService.bookClass(u2.getId(), fc.getId()); // goes to waitlist

        bookingService.cancelBooking(u1.getId(), fc.getId());

        assertFalse(u1.getBookedClassIds().contains(fc.getId()));
        assertTrue(u2.getBookedClassIds().contains(fc.getId()));
    }

    @Test
    void cancelWithoutBookingThrowsException() {
        User user = userService.register("jim", "xyz", UserTier.GOLD);
        FitnessClass fc = classService.createClass(ClassType.GYM, 2, LocalDateTime.now().plusDays(1));

        assertThrows(RuntimeException.class, () -> bookingService.cancelBooking(user.getId(), fc.getId()));
    }
}
