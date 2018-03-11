package com.gmail.at.sichyuriyy.user.repository;

import com.gmail.at.sichyuriyy.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);
}
