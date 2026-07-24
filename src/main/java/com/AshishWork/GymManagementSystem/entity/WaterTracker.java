package com.AshishWork.GymManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "daily_metrics")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class WaterTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int waterMlConsumed;
    private int stepWalked;
    private int caloriesBurned;
    private LocalDate date;
}
