package com.phonepe.fitness_booking_system.model;

import com.phonepe.fitness_booking_system.utils.ClassType;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class FitnessClass {
    static AtomicInteger idGen = new AtomicInteger();
    int id;
    ClassType type;
    int capacity;
    LocalDateTime startTime;
    Set<Integer> bookedUserIds = new HashSet<>();
    Queue<Integer> waitlist = new LinkedList<>();

    public FitnessClass(ClassType type, int capacity, LocalDateTime startTime) {
        this.id = idGen.incrementAndGet();
        this.type = type;
        this.capacity = capacity;
        this.startTime = startTime;
    }

    public boolean isFull() {
        return bookedUserIds.size() >= capacity;
    }

    public boolean canCancel() {
        return LocalDateTime.now().isBefore(startTime.minusMinutes(30));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClassType getType() {
        return type;
    }

    public void setType(ClassType type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Set<Integer> getBookedUserIds() {
        return bookedUserIds;
    }

    public void setBookedUserIds(Set<Integer> bookedUserIds) {
        this.bookedUserIds = bookedUserIds;
    }

    public Queue<Integer> getWaitlist() {
        return waitlist;
    }

    public void setWaitlist(Queue<Integer> waitlist) {
        this.waitlist = waitlist;
    }

}