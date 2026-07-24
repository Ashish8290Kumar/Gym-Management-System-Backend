package com.AshishWork.GymManagementSystem.controller;

import com.AshishWork.GymManagementSystem.entity.Attendance;
import com.AshishWork.GymManagementSystem.entity.ProgressTracker;
import com.AshishWork.GymManagementSystem.entity.WaterTracker;
import com.AshishWork.GymManagementSystem.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/track")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor

public class TrackingController {

    private final TrackingService trackingService;

    @PostMapping("/attendance/checkin")
    public ResponseEntity<Attendance> checkIn(@RequestParam Long userId) {
        return ResponseEntity.ok(trackingService.logAttendance(userId));
    }

    @PostMapping("/progress/save")
    public ResponseEntity<ProgressTracker> trackProgress(@RequestParam Long userId, @RequestBody ProgressTracker tracker) {
        return  ResponseEntity.ok(trackingService.logProgress(userId, tracker));
    }

    @PostMapping("/metrics/sync")
    public ResponseEntity<WaterTracker> syncDailyMetrics(@RequestParam Long userId, @RequestBody WaterTracker tracker) {
        return ResponseEntity.ok(trackingService.logDailyMetrics(userId, tracker));
    }
}
