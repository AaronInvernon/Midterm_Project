package com.ironhack.Midterm.Project.repository;

import com.ironhack.Midterm.Project.model.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Integer> {

    @Query("SELECT c FROM Checking c JOIN AccountHolders a ON c.primaryOwner = a.id JOIN  AccountHolders a2 ON c.secondaryOwner = a2.id WHERE c.id = :id AND (a.name = :name OR a2.name = :name)")
    public Checking findCheckingByName(@Param(value="name") String name, @Param(value="id")Integer id);
}
