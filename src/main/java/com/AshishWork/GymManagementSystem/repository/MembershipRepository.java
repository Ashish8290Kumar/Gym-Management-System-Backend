package com.AshishWork.GymManagementSystem.repository;

import com.AshishWork.GymManagementSystem.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    Optional<Membership> findByUserId(Long userId);
}
