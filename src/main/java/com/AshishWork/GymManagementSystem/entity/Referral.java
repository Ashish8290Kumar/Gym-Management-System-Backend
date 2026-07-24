package com.AshishWork.GymManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "referrals")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Referral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "referrer_id", nullable = false)
    private User referrer;

    private String referrerEmail;
    private String status;
}
