package com.AshishWork.GymManagementSystem.service;

import com.AshishWork.GymManagementSystem.entity.*;

public interface GymService {
    User registerUser (User user);
    User updateUserProfile (Long id, User updatedUser);
    Membership purchaseMembership (Long userId, String plan);
    Payment processPayment(Long userId, Double amount, String txId);
    Booking scheduleSession(Long userId, Booking booking);
    WorkoutPlan assignWorkout(Long userId, WorkoutPlan plan);
    DietPlan assignDiet(Long userId, DietPlan plan);

    WorkoutPlan assignWorkoutByUsername(String username, WorkoutPlan plan);
    DietPlan assignDietByUsername(String username, DietPlan plan);
}
