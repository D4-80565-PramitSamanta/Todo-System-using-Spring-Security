package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.User;

public interface UserDAO extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String name);
	boolean existsByEmail(String email);
	Optional<User> findByUsernameOrEmail (String username, String email);
}
