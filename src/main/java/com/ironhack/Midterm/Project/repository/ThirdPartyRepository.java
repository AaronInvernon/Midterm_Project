package com.ironhack.Midterm.Project.repository;

import com.ironhack.Midterm.Project.model.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Integer> {
}
