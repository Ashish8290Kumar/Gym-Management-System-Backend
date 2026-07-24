package com.AshishWork.GymManagementSystem.service;

import com.AshishWork.GymManagementSystem.dto.LoginRequest;
import com.AshishWork.GymManagementSystem.dto.LoginResponse;
import com.AshishWork.GymManagementSystem.dto.RegisterRequest;
import com.AshishWork.GymManagementSystem.dto.RegisterResponse;
import jakarta.servlet.Registration;

public interface AuthService {
    RegisterResponse registerNewUser(RegisterRequest registerRequest);
    LoginResponse authenticateUser(LoginRequest loginRequest);
}
