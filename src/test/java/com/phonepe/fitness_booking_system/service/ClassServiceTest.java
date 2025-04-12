package com.phonepe.fitness_booking_system.service;

import com.phonepe.fitness_booking_system.model.FitnessClass;
import com.phonepe.fitness_booking_system.utils.ClassType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClassServiceTest {

    private ClassService classService;

    @BeforeEach
    void setup() {
        classService = new ClassService();
    }

    @Test
    void createClassSuccessfully() {
        FitnessClass fc = classService.createClass(ClassType.YOGA, 10, LocalDateTime.now().plusDays(1));
        assertNotNull(fc);
        assertEquals(ClassType.YOGA, fc.getType());
        assertEquals(10, fc.getCapacity());
    }

    @Test
    void getClassByIdReturnsCorrectClass() {
        FitnessClass fc = classService.createClass(ClassType.GYM, 5, LocalDateTime.now().plusDays(2));
        assertEquals(fc, classService.getClassById(fc.getId()));
    }

    @Test
    void cancelClassRemovesIt() {
        FitnessClass fc = classService.createClass(ClassType.DANCE, 20, LocalDateTime.now().plusDays(3));
        classService.cancelClass(fc.getId());
        assertNull(classService.getClassById(fc.getId()));
    }

    @Test
    void getAllClassesReturnsAll() {
        classService.createClass(ClassType.YOGA, 8, LocalDateTime.now().plusDays(1));
        classService.createClass(ClassType.GYM, 6, LocalDateTime.now().plusDays(1));
        List<FitnessClass> all = classService.getAllClasses();
        assertEquals(2, all.size());
    }
}
