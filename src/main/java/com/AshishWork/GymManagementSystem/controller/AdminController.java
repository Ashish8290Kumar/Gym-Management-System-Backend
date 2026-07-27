package com.AshishWork.GymManagementSystem.controller;

import com.AshishWork.GymManagementSystem.entity.DietPlan;
import com.AshishWork.GymManagementSystem.entity.WorkoutPlan;
import com.AshishWork.GymManagementSystem.service.GymService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor

public class AdminController {

    private final GymService gymService;

    @PostMapping("/workout/assign")
    public ResponseEntity<WorkoutPlan> assignWorkout(@RequestParam String username, @RequestBody WorkoutPlan Plan) {
        return ResponseEntity.ok(gymService.assignWorkoutByUsername(username, Plan));
    }

    @PostMapping("/diet/assign")
    public ResponseEntity<DietPlan> assignDiet(@RequestParam String username ,@RequestBody DietPlan Plan) {
        return ResponseEntity.ok(gymService.assignDietByUsername(username, Plan));
    }
}
