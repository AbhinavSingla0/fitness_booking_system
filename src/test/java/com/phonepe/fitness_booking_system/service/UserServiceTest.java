package com.phonepe.fitness_booking_system.service;

import com.phonepe.fitness_booking_system.model.User;
import com.phonepe.fitness_booking_system.utils.UserTier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService();
    }

    @Test
    void registerNewUserSuccessfully() {
        User user = userService.register("john", "pass123", UserTier.GOLD);
        assertNotNull(user);
        assertEquals("john", user.getUsername());
        assertEquals(UserTier.GOLD, user.getTier());
    }

    @Test
    void registerDuplicateUserThrowsException() {
        userService.register("john", "pass123", UserTier.SILVER);
        assertThrows(RuntimeException.class, () -> {
            userService.register("john", "another", UserTier.PLATINUM);
        });
    }

    @Test
    void loginWithValidCredentials() {
        User registered = userService.register("alice", "secure", UserTier.GOLD);
        User loggedIn = userService.login("alice", "secure");
        assertEquals(registered, loggedIn);
    }

    @Test
    void loginWithInvalidCredentialsThrowsException() {
        userService.register("bob", "correct", UserTier.SILVER);
        assertThrows(RuntimeException.class, () -> userService.login("bob", "wrong"));
    }

    @Test
    void getUserByIdReturnsCorrectUser() {
        User user = userService.register("david", "pw", UserTier.PLATINUM);
        assertEquals(user, userService.getById(user.getId()));
    }
}
