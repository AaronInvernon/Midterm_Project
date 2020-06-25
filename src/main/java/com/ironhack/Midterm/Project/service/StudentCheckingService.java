package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.exceptions.DataNotFoundException;
import com.ironhack.Midterm.Project.model.Checking;
import com.ironhack.Midterm.Project.model.Money;
import com.ironhack.Midterm.Project.model.StudentChecking;
import com.ironhack.Midterm.Project.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class StudentCheckingService {

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    public StudentChecking findById(Integer id){
        return studentCheckingRepository.findById(id).orElseThrow(()-> new DataNotFoundException("Student checking account not found"));
    }

    public BigDecimal findBalanceById(Integer id){
        return findById(id).getBalance().getAmount();
    }

    public void credit(Integer id,String amount){
        StudentChecking sc = findById(id);
        Money m = new Money(new BigDecimal(amount));
        sc.credit(m);
        studentCheckingRepository.save(sc);
    }

    public void debit(Integer id,String amount){
        StudentChecking sc = findById(id);
        Money m = new Money(new BigDecimal(amount));
        sc.debit(m);
        studentCheckingRepository.save(sc);
    }
}
