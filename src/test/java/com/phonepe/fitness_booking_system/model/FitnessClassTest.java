package com.phonepe.fitness_booking_system.model;

import com.phonepe.fitness_booking_system.utils.ClassType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FitnessClassTest {

    private FitnessClass yogaClass;

    @BeforeEach
    void setUp() {
        yogaClass = new FitnessClass(ClassType.YOGA, 2, LocalDateTime.now().plusHours(1));
    }

    @Test
    void testInitialCapacity() {
        assertFalse(yogaClass.isFull());
    }

    @Test
    void testIsFullWhenCapacityReached() {
        yogaClass.getBookedUserIds().add(1);
        yogaClass.getBookedUserIds().add(2);
        assertTrue(yogaClass.isFull());
    }

    @Test
    void testWaitlistBehavior() {
        yogaClass.getWaitlist().add(10);
        yogaClass.getWaitlist().add(11);

        assertEquals(2, yogaClass.getWaitlist().size());
        assertEquals(10, yogaClass.getWaitlist().peek());
    }

    @Test
    void setWaitlistTEst() {
        yogaClass.setWaitlist(null);
    }

    @Test
    void testCanCancelBefore30Minutes() {
        FitnessClass fc = new FitnessClass(ClassType.DANCE, 5, LocalDateTime.now().plusMinutes(45));
        assertTrue(fc.canCancel());
    }

    @Test
    void testCannotCancelWithin30Minutes() {
        FitnessClass fc = new FitnessClass(ClassType.DANCE, 5, LocalDateTime.now().plusMinutes(20));
        assertFalse(fc.canCancel());
    }

    @Test
    void setIdTest() {
        FitnessClass fc = new FitnessClass(ClassType.DANCE, 0, LocalDateTime.now().plusMinutes(20));
        fc.setId(1);

    }

    @Test
    void setTypeTest() {
        FitnessClass fc = new FitnessClass(ClassType.DANCE, 0, LocalDateTime.now().plusMinutes(20));
        fc.setType(ClassType.GYM);

    }

    @Test
    void setCapacityTest() {
        FitnessClass fc = new FitnessClass(ClassType.DANCE, 0, LocalDateTime.now().plusMinutes(20));
        fc.setCapacity(1);

    }

    @Test
    void getStartTimeTest() {
        FitnessClass fc = new FitnessClass(ClassType.DANCE, 0, LocalDateTime.now().plusMinutes(20));
        fc.getStartTime();

    }

    @Test
    void setStartTimeTest() {
        FitnessClass fc = new FitnessClass(ClassType.DANCE, 0, LocalDateTime.now().plusMinutes(20));
        fc.setStartTime(LocalDateTime.now().plusMinutes(30));

    }

    @Test
    void setBookedUserIdsTest() {
        Set<Integer> bookedUserIds = new HashSet<>();
        FitnessClass fc = new FitnessClass(ClassType.DANCE, 0, LocalDateTime.now().plusMinutes(20));
        fc.setBookedUserIds(bookedUserIds);

    }

    @Test
    void getBookedUserIdsTest() {
        FitnessClass fc = new FitnessClass(ClassType.DANCE, 0, LocalDateTime.now().plusMinutes(20));
        fc.getBookedUserIds();

    }

}
