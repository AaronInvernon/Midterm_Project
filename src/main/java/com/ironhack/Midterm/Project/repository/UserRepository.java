package com.ironhack.Midterm.Project.repository;

import com.ironhack.Midterm.Project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);

    public User findByName(String name);
}
