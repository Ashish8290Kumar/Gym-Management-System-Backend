package com.AshishWork.GymManagementSystem.service;

import com.AshishWork.GymManagementSystem.entity.Attendance;
import com.AshishWork.GymManagementSystem.entity.ProgressTracker;
import com.AshishWork.GymManagementSystem.entity.WaterTracker;

public interface TrackingService {
    Attendance logAttendance(Long userId);
    ProgressTracker logProgress(Long userId, ProgressTracker stats);
    WaterTracker logDailyMetrics(Long userId,WaterTracker metrics);
}
