package com.ironhack.Midterm.Project.repository;

import com.ironhack.Midterm.Project.model.Checking;
import com.ironhack.Midterm.Project.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query(value= "SELECT * FROM transaction WHERE account_id=:id ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Transaction findLastTransaction(@Param("id") Integer id);
    @Query(value = "SELECT COUNT(*) FROM transaction WHERE account_id=:id AND date_at>=:startOfDay ORDER BY COUNT(*) LIMIT 1", nativeQuery = true)
    Integer todayTotal(@Param("id") Integer id, @Param("startOfDay") LocalDate startOfDay);
    @Query(value = "SELECT COUNT(*) FROM transaction WHERE account_id=:id AND date_at<:startOfDay GROUP BY date_at ORDER BY COUNT(*) DESC LIMIT 1", nativeQuery = true)
    Integer maxTotal(@Param("id") Integer id, @Param("startOfDay") LocalDate startOfDay);
}

