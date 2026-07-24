package com.AshishWork.GymManagementSystem.repository;

import com.AshishWork.GymManagementSystem.entity.WaterTracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaterRepository extends JpaRepository<WaterTracker, Long> {
    List<WaterTracker> findByUserId(Long userId);
}
