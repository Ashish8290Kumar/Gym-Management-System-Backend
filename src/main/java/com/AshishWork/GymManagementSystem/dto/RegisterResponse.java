package com.AshishWork.GymManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RegisterResponse {
    private String message;
    private Boolean success;
    private Long userId;
    private String username;
    private String role;

}
