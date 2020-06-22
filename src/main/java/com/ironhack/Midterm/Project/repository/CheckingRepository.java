package com.ironhack.Midterm.Project.repository;

import com.ironhack.Midterm.Project.model.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Integer> {

}
