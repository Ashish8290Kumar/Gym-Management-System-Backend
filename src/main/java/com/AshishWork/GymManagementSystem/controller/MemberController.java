package com.AshishWork.GymManagementSystem.controller;

import com.AshishWork.GymManagementSystem.entity.Booking;
import com.AshishWork.GymManagementSystem.entity.Membership;
import com.AshishWork.GymManagementSystem.entity.Payment;
import com.AshishWork.GymManagementSystem.entity.User;
import com.AshishWork.GymManagementSystem.service.GymService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class MemberController {

    private final GymService gymService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
     return ResponseEntity.ok(gymService.registerUser(user));
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(gymService.updateUserProfile(id, user));
    }

    @PostMapping("/membership/buy")
    public ResponseEntity<Membership> buyMembership(@RequestParam Long userId, @RequestParam String plan) {
        return ResponseEntity.ok(gymService.purchaseMembership(userId, plan));
    }

    @PostMapping("/payment/checkout")
    public ResponseEntity<Payment> checkout(@RequestParam Long userId, @RequestParam Double amount, @RequestParam String txId) {
     return ResponseEntity.ok(gymService.processPayment(userId, amount, txId));
    }

    @PostMapping("/booking/create")
    public ResponseEntity<Booking> createBooking(@RequestParam Long userId ,@RequestBody Booking booking) {
        return ResponseEntity.ok(gymService.scheduleSession(userId, booking));
    }
}
