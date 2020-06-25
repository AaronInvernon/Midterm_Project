package com.ironhack.Midterm.Project.repository;

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

    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.account=:id AND t.date BETWEEN CURRENT_TIME AND :startOfDay")
    Integer todayTotal(@Param("id") Integer id, @Param("startOfDay") LocalDate startOfDay);
    @Query(value = "SELECT COUNT(t) FROM transaction t WHERE CAST(t.date AS DATE)!=NOW() && account=:id GROUP BY CAST(t.date AS DATE), account ORDER BY COUNT(t) DESC LIMIT 1", nativeQuery = true)
    Integer maxTotal(@Param("id") Integer id);
}
