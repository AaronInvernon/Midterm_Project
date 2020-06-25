package com.ironhack.Midterm.Project.repository;

import com.ironhack.Midterm.Project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByUser_Id(Long id);
}