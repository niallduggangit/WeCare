package com.infosys.wecare.coach.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.wecare.coach.entities.Coach;

public interface CoachRepository extends JpaRepository<Coach, String> {
}
