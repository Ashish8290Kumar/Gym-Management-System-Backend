package com.AshishWork.GymManagementSystem.repository;

import com.AshishWork.GymManagementSystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserId(Long userId); }
