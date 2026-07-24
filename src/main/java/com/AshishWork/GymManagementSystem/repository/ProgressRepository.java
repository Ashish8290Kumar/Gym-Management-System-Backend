package com.AshishWork.GymManagementSystem.repository;

import com.AshishWork.GymManagementSystem.entity.ProgressTracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressRepository extends JpaRepository<ProgressTracker,Long> {
    List<ProgressTracker> findByUserId(Long userId);
}

