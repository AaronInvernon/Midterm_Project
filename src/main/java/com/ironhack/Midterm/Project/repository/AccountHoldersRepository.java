package com.ironhack.Midterm.Project.repository;

import com.ironhack.Midterm.Project.model.AccountHolders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHoldersRepository extends JpaRepository<AccountHolders, Integer> {
    public AccountHolders  findByName(String name);
}
