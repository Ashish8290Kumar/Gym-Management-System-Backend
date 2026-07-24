package com.AshishWork.GymManagementSystem.repository;

import com.AshishWork.GymManagementSystem.entity.DietPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DietPlanRepository extends JpaRepository<DietPlan, Long> {
    List<DietPlan> findByUserId(Long userId);
}
