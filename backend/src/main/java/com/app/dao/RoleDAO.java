package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Role;

public interface RoleDAO extends JpaRepository<Role,Long>{

}
