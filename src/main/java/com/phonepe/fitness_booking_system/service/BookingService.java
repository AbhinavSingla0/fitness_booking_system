package com.phonepe.fitness_booking_system.service;

import com.phonepe.fitness_booking_system.model.FitnessClass;
import com.phonepe.fitness_booking_system.model.User;

public class BookingService {
    UserService userService;
    ClassService classService;

    public BookingService(UserService us, ClassService cs) {
        this.userService = us;
        this.classService = cs;
    }

    public synchronized void bookClass(int userId, int classId) {
        User user = userService.getById(userId);
        FitnessClass fc = classService.getClassById(classId);

        if (user.getBookedClassIds().contains(classId))
            throw new RuntimeException("Already booked");

        if (!user.canBook())
            throw new RuntimeException("Booking limit reached");

        if (!fc.isFull()) {
            fc.getBookedUserIds().add(userId);
            user.bookClass(classId);
        } else {
            fc.getWaitlist().add(userId);
        }
    }

    public synchronized void cancelBooking(int userId, int classId) {
        User user = userService.getById(userId);
        FitnessClass fc = classService.getClassById(classId);

        if (!user.getBookedClassIds().contains(classId))
            throw new RuntimeException("Booking not found");

        if (!fc.canCancel())
            throw new RuntimeException("Too late to cancel");

        user.cancelClass(classId);
        fc.getBookedUserIds().remove(userId);

        if (!fc.getWaitlist().isEmpty()) {
            int nextUserId = fc.getWaitlist().poll();
            fc.getBookedUserIds().add(nextUserId);
            userService.getById(nextUserId).bookClass(classId);
        }
    }
}