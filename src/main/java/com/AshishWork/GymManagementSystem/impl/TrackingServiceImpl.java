package com.AshishWork.GymManagementSystem.impl;

import com.AshishWork.GymManagementSystem.entity.Attendance;
import com.AshishWork.GymManagementSystem.entity.ProgressTracker;
import com.AshishWork.GymManagementSystem.entity.User;
import com.AshishWork.GymManagementSystem.entity.WaterTracker;
import com.AshishWork.GymManagementSystem.repository.AttendanceRepository;
import com.AshishWork.GymManagementSystem.repository.ProgressRepository;
import com.AshishWork.GymManagementSystem.repository.UserRepository;
import com.AshishWork.GymManagementSystem.repository.WaterRepository;
import com.AshishWork.GymManagementSystem.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class TrackingServiceImpl implements TrackingService {

    private final AttendanceRepository attendanceRepo;
    private final ProgressRepository progressRepo;
    private final WaterRepository waterRepo;
    private final UserRepository userRepo;


    @Override
    public Attendance logAttendance(Long userId) {
        User user=userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("User validation failed for attendance."));
        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setDate(LocalDate.now());
        attendance.setCheckInTime(LocalTime.now());
        attendance.setCheckOutTime(LocalTime.now());
        attendance.setStatus("PRESENT");
        return attendanceRepo.save(attendance);
    }

    @Override
    public ProgressTracker logProgress(Long userId, ProgressTracker stats) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("User log validation failed."));
        stats.setUser(user);
        stats.setLogDate(LocalDate.now());

        double heightInMeters = stats.getHeightCm() / 100.0;
        if (heightInMeters > 0) {
            stats.setBmi(stats.getWeightKg() / (heightInMeters * heightInMeters));
        }
        return progressRepo.save(stats);
    }

    @Override
    public WaterTracker logDailyMetrics(Long userId, WaterTracker metrics) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("Invalid user configuration context."));
        metrics.setUser(user);
        metrics.setDate(LocalDate.now());
        return waterRepo.save(metrics);
    }
}
