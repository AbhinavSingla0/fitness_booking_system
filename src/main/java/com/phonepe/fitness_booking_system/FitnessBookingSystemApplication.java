package com.phonepe.fitness_booking_system;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.phonepe.fitness_booking_system.utils.ClassType;
import com.phonepe.fitness_booking_system.utils.UserTier;
import com.phonepe.fitness_booking_system.model.FitnessClass;
import com.phonepe.fitness_booking_system.model.User;
import com.phonepe.fitness_booking_system.service.BookingService;
import com.phonepe.fitness_booking_system.service.ClassService;
import com.phonepe.fitness_booking_system.service.UserService;

@SpringBootApplication
public class FitnessBookingSystemApplication {

	public static void main(String[] args) {
		UserService userService = new UserService();
		ClassService classService = new ClassService();
		BookingService bookingService = new BookingService(userService, classService);

		User u1 = userService.register("alice", "pass", UserTier.PLATINUM);
		User u2 = userService.register("bob", "pass", UserTier.SILVER);

		FitnessClass fc1 = classService.createClass(ClassType.YOGA, 1, LocalDateTime.now().plusHours(1));

		bookingService.bookClass(u1.getId(), fc1.getId()); // alice books
		bookingService.bookClass(u2.getId(), fc1.getId()); // bob goes to waitlist

		bookingService.cancelBooking(u1.getId(), fc1.getId()); // bob should be promoted from waitlist

		System.out.println("Class bookings: " + fc1.getBookedUserIds()); // Should have only bob
		System.out.println("Waitlist: " + fc1.getWaitlist()); // Should be empty
	}

}
