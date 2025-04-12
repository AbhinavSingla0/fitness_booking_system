package com.phonepe.fitness_booking_system.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassTypeTest {

    @Test
    void testEnumValues() {
        assertEquals(ClassType.YOGA, ClassType.valueOf("YOGA"));
        assertEquals(ClassType.GYM, ClassType.valueOf("GYM"));
        assertEquals(ClassType.DANCE, ClassType.valueOf("DANCE"));
    }

    @Test
    void testEnumNames() {
        assertEquals("YOGA", ClassType.YOGA.name());
        assertEquals("GYM", ClassType.GYM.name());
        assertEquals("DANCE", ClassType.DANCE.name());
    }
}
