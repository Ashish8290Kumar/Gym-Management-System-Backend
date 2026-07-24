package com.AshishWork.GymManagementSystem.impl;

import com.AshishWork.GymManagementSystem.entity.*;
import com.AshishWork.GymManagementSystem.repository.*;
import com.AshishWork.GymManagementSystem.service.GymService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService {

    private final UserRepository userRepo;
    private final MembershipRepository membershipRepo;
    private final PaymentRepository paymentRepo;
    private final WorkoutRepository workoutRepo;
    private final BookingRepository bookingRepo;
    private final DietPlanRepository dietRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


    @Override
    public User updateUserProfile(Long id, User updatedUser) {
        User user = userRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("User Profile Not Found"));
        user.setUsername(updatedUser.getUsername());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        user.setAge(updatedUser.getAge());
        user.setGender(updatedUser.getGender());
        return userRepo.save(user);
    }

    @Override
    public Membership purchaseMembership(Long userId, String plan) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("User entity missing."));
        Membership membership = new Membership();
        membership.setUser(user);
        membership.setPlantype(plan);
        membership.setStatus("ACTIVE");
        membership.setStartDate(java.time.LocalDate.now());
        membership.setEndDate(java.time.LocalDate.now().plusMonths(1));
        return membershipRepo.save(membership);
    }

    @Override
    public Payment processPayment(Long userId, Double amount, String txId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("User account missing."));
        Payment payment = new Payment(null,user,amount,txId,"SUCCESS", LocalDateTime.now());
        return paymentRepo.save(payment);
    }

    @Override
    public Booking scheduleSession(Long userId, Booking booking) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("User identity verification failed."));
        booking.setUser(user);
        booking.setStatus("BOOKED");
        return bookingRepo.save(booking);
    }

    @Override
    public WorkoutPlan assignWorkout(Long userId, WorkoutPlan plan) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("Target user allocation error."));
        plan.setUser(user);
        return workoutRepo.save(plan);
    }

    @Override
    public DietPlan assignDiet(Long userId, DietPlan plan) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("Target user allocation error."));
        plan.setUser(user);
        return dietRepo.save(plan);
    }

    @Override
    public WorkoutPlan assignWorkoutByUsername(String username, WorkoutPlan plan) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Error: Gym member not found with username: " + username));

        plan.setUser(user);
        return workoutRepo.save(plan);
    }

    @Override
    public DietPlan assignDietByUsername(String username, DietPlan plan) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error: Gym member not found with username: " + username));
        plan.setUser(user);
        return dietRepo.save(plan);
    }


}
