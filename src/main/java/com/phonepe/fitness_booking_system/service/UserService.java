package com.phonepe.fitness_booking_system.service;

import java.util.HashMap;
import java.util.Map;

import com.phonepe.fitness_booking_system.model.User;
import com.phonepe.fitness_booking_system.utils.UserTier;

public class UserService {
    Map<String, User> usersByUsername = new HashMap<>();
    Map<Integer, User> usersById = new HashMap<>();

    public User register(String username, String password, UserTier tier) {
        if (usersByUsername.containsKey(username))
            throw new RuntimeException("User exists");
        User user = new User(username, password, tier);
        usersByUsername.put(username, user);
        usersById.put(user.getId(), user);
        return user;
    }

    public User login(String username, String password) {
        User user = usersByUsername.get(username);
        if (user == null || !user.getPassword().equals(password))
            throw new RuntimeException("Invalid login");
        return user;
    }

    public User getById(int userId) {
        return usersById.get(userId);
    }
}