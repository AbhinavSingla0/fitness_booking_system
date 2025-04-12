package com.phonepe.fitness_booking_system.model;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.phonepe.fitness_booking_system.utils.UserTier;

public class User {
    static AtomicInteger idGen = new AtomicInteger();
    int id;
    String username;
    String password;
    UserTier tier;
    Set<Integer> bookedClassIds = new HashSet<>();

    public User(String username, String password, UserTier tier) {
        this.id = idGen.incrementAndGet();
        this.username = username;
        this.password = password;
        this.tier = tier;
    }

    public boolean canBook() {
        return bookedClassIds.size() < tier.getBookingLimit();
    }

    public void bookClass(int classId) {
        bookedClassIds.add(classId);
    }

    public void cancelClass(int classId) {
        bookedClassIds.remove(classId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserTier getTier() {
        return tier;
    }

    public void setTier(UserTier tier) {
        this.tier = tier;
    }

    public Set<Integer> getBookedClassIds() {
        return bookedClassIds;
    }

    public void setBookedClassIds(Set<Integer> bookedClassIds) {
        this.bookedClassIds = bookedClassIds;
    }
}