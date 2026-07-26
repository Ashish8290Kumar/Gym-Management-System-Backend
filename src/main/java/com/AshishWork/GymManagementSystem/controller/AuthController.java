package com.AshishWork.GymManagementSystem.controller;

import com.AshishWork.GymManagementSystem.dto.LoginRequest;
import com.AshishWork.GymManagementSystem.dto.LoginResponse;
import com.AshishWork.GymManagementSystem.dto.RegisterRequest;
import com.AshishWork.GymManagementSystem.dto.RegisterResponse;
import com.AshishWork.GymManagementSystem.entity.User;
import com.AshishWork.GymManagementSystem.jwt.JwtUtils;
import com.AshishWork.GymManagementSystem.service.AuthService;
import com.AshishWork.GymManagementSystem.service.GymService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

   private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register (@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.registerNewUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
      return ResponseEntity.ok(authService.authenticateUser(request));
    }

}
