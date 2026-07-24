package com.AshishWork.GymManagementSystem.repository;

import com.AshishWork.GymManagementSystem.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<WorkoutPlan,Long> {
    List<WorkoutPlan> findByUserId(Long userId);

}
