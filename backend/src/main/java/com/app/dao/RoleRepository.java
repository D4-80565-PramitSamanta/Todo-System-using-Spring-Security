package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String string);

}