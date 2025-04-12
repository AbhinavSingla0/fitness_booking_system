package com.phonepe.fitness_booking_system.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.phonepe.fitness_booking_system.model.FitnessClass;
import com.phonepe.fitness_booking_system.utils.ClassType;

public class ClassService {
    Map<Integer, FitnessClass> classes = new HashMap<>();

    public FitnessClass createClass(ClassType type, int capacity, LocalDateTime time) {
        FitnessClass fc = new FitnessClass(type, capacity, time);
        classes.put(fc.getId(), fc);
        return fc;
    }

    public void cancelClass(int classId) {
        classes.remove(classId);
    }

    public FitnessClass getClassById(int classId) {
        return classes.get(classId);
    }

    public List<FitnessClass> getAllClasses() {
        return new ArrayList<>(classes.values());
    }
}
