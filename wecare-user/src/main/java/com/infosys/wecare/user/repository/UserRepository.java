package com.infosys.wecare.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.wecare.user.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
}
