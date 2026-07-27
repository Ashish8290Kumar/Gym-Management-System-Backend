package com.AshishWork.GymManagementSystem.impl;

import com.AshishWork.GymManagementSystem.Enum.Role;
import com.AshishWork.GymManagementSystem.dto.LoginRequest;
import com.AshishWork.GymManagementSystem.dto.LoginResponse;
import com.AshishWork.GymManagementSystem.dto.RegisterRequest;
import com.AshishWork.GymManagementSystem.dto.RegisterResponse;
import com.AshishWork.GymManagementSystem.entity.User;
import com.AshishWork.GymManagementSystem.jwt.JwtUtils;
import com.AshishWork.GymManagementSystem.repository.UserRepository;
import com.AshishWork.GymManagementSystem.security.CustomUserDetails;
import com.AshishWork.GymManagementSystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Override
    public RegisterResponse registerNewUser(RegisterRequest request) {

        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            return new RegisterResponse("Username is already taken!", false, null, null, null);
        }
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            return new RegisterResponse("Email identity is already registered!", false, null, null, null);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAge(request.getAge());


        if (request.getGender() != null) {
            user.setGender(request.getGender().toUpperCase());
        }

        try {
            user.setRole(Role.valueOf(request.getRole().toUpperCase()));
        } catch (Exception e) {
            user.setRole(Role.MEMBER);
        }

        User savedUser = userRepo.save(user);

        return new RegisterResponse(
                "User registered successfully!",
                true,
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getRole().name()
        );
    }

    @Override
    public LoginResponse authenticateUser(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(request.getUsername());
        User userEntity = userDetails.getUserEntity();
        String jwtToken = jwtUtils.generateToken(userDetails);

        return new LoginResponse(
                jwtToken,
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getRole().name()
        );
    }
}
