package com.AshishWork.GymManagementSystem.repository;

import com.AshishWork.GymManagementSystem.entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferralRepository extends JpaRepository<Referral, Long> {
    List<Referral> findByReferrerId(Long referrerId);
}
