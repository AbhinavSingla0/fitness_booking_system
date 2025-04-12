package com.phonepe.fitness_booking_system;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.phonepe.fitness_booking_system.model.FitnessClass;
import com.phonepe.fitness_booking_system.model.User;
import com.phonepe.fitness_booking_system.service.BookingService;
import com.phonepe.fitness_booking_system.service.ClassService;
import com.phonepe.fitness_booking_system.service.UserService;
import com.phonepe.fitness_booking_system.utils.ClassType;
import com.phonepe.fitness_booking_system.utils.UserTier;

public class FitnessBookingSystemApplicationTests {

	private UserService userService;
	private ClassService classService;
	private BookingService bookingService;

	@BeforeEach
	public void setup() {
		userService = new UserService();
		classService = new ClassService();
		bookingService = new BookingService(userService, classService);
	}

	@Test
	public void testBookingAndWaitlistPromotion() {
		// Register users
		User u1 = userService.register("alice", "pass", UserTier.PLATINUM);
		User u2 = userService.register("bob", "pass", UserTier.SILVER);

		// Create class with 1 seat
		FitnessClass fc = classService.createClass(ClassType.YOGA, 1, LocalDateTime.now().plusHours(1));

		// Book class for alice
		bookingService.bookClass(u1.getId(), fc.getId());
		assertTrue(fc.getBookedUserIds().contains(u1.getId()));
		assertFalse(fc.getWaitlist().contains(u2.getId()));

		// Book class for bob (should go to waitlist)
		bookingService.bookClass(u2.getId(), fc.getId());
		assertEquals(1, fc.getBookedUserIds().size());
		assertTrue(fc.getWaitlist().contains(u2.getId()));

		// Cancel alice booking (bob should be promoted)
		bookingService.cancelBooking(u1.getId(), fc.getId());

		assertFalse(fc.getBookedUserIds().contains(u1.getId()));
		assertTrue(fc.getBookedUserIds().contains(u2.getId()));
		assertTrue(u2.getBookedClassIds().contains(fc.getId()));
		assertTrue(fc.getWaitlist().isEmpty());
	}

	@Test
	public void testBookingLimitReached() {
		User user = userService.register("john", "123", UserTier.SILVER);

		// Silver user can only book 3 classes
		for (int i = 0; i < 3; i++) {
			FitnessClass fc = classService.createClass(ClassType.DANCE, 10, LocalDateTime.now().plusHours(i + 1));
			bookingService.bookClass(user.getId(), fc.getId());
		}

		FitnessClass extraClass = classService.createClass(ClassType.YOGA, 10, LocalDateTime.now().plusHours(4));
		RuntimeException ex = assertThrows(RuntimeException.class,
				() -> bookingService.bookClass(user.getId(), extraClass.getId()));
		assertEquals("Booking limit reached", ex.getMessage());
	}

	@Test
	public void testAlreadyBooked() {
		User user = userService.register("john", "pass", UserTier.PLATINUM);
		FitnessClass fc = classService.createClass(ClassType.DANCE, 5, LocalDateTime.now().plusHours(1));
		bookingService.bookClass(user.getId(), fc.getId());

		RuntimeException ex = assertThrows(RuntimeException.class,
				() -> bookingService.bookClass(user.getId(), fc.getId()));
		assertEquals("Already booked", ex.getMessage());
	}

	@Test
	public void testTooLateToCancel() {
		User user = userService.register("john", "pass", UserTier.PLATINUM);
		FitnessClass fc = classService.createClass(ClassType.DANCE, 5, LocalDateTime.now().plusMinutes(10));
		bookingService.bookClass(user.getId(), fc.getId());

		RuntimeException ex = assertThrows(RuntimeException.class,
				() -> bookingService.cancelBooking(user.getId(), fc.getId()));
		assertEquals("Too late to cancel", ex.getMessage());
	}

	@Test
	public void testClassCreationAndCancel() {
		FitnessClass fc = classService.createClass(ClassType.DANCE, 20, LocalDateTime.now().plusDays(1));
		int id = fc.getId();

		assertNotNull(classService.getClassById(id));

		classService.cancelClass(id);

		assertNull(classService.getClassById(id));
	}
}
