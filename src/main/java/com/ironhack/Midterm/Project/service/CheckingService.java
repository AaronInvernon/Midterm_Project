package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.dto.CheckingPrimaryOwner;
import com.ironhack.Midterm.Project.dto.Transference;
import com.ironhack.Midterm.Project.exceptions.DataNotFoundException;
import com.ironhack.Midterm.Project.exceptions.NoAccessException;
import com.ironhack.Midterm.Project.exceptions.NotLoggedException;
import com.ironhack.Midterm.Project.model.*;
import com.ironhack.Midterm.Project.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.rmi.AccessException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class CheckingService {

    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private AccountHoldersRepository accountHoldersRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

    private AccountHolders accountHolder;
    private AccountHolders secondaryOwner;
    private LocalDate today;
    private StudentChecking newStudentChecking;
    private Checking newChecking;
    private  final Logger LOGGER = LogManager.getLogger(CheckingService.class);

    public Checking create(Integer accountHolderId, CheckingPrimaryOwner checking) throws DataNotFoundException {
        LOGGER.info("[INIT] - create a checking account");
        today = LocalDate.now();
        accountHolder = accountHoldersRepository.findById(accountHolderId).orElseThrow(() -> new DataNotFoundException("User not found"));
        if(checking.getSecondaryOwner() != null){
            LOGGER.info("checking didn't have a secondaryOwner");
            newChecking.setSecondaryOwner(accountHoldersRepository.findById(secondaryOwner.getId()).orElseThrow(() -> new DataNotFoundException("Owner not found")));
            newStudentChecking.setSecondaryOwner(accountHoldersRepository.findById(secondaryOwner.getId()).orElseThrow(() -> new DataNotFoundException("Owner not found")));
        }
        if(Period.between(accountHolder.getDateOfBirth(), today).getYears() < 24){
            LOGGER.info("The owner have less than 24, create a studentChecking");
            newStudentChecking = new StudentChecking(checking.getBalance(), checking.getSecretKey(), accountHolder);
            newStudentChecking.setMinimumBalance(new BigDecimal("0"));
            newStudentChecking.setMonthlyMaintenanceFee(new BigDecimal("0"));
            LOGGER.info("[END] - create a studentChecking");
            return studentCheckingRepository.save(newStudentChecking);
        }
        newChecking = new StudentChecking(checking.getBalance(), checking.getSecretKey(), accountHolder);
        LOGGER.info("[END] - create a checking account");
        return checkingRepository.save(newChecking);
    }

    public Checking findById(Integer id, User user){
        if(!user.isLogged()) throw new NotLoggedException("No user logged id");
        Checking c = checkingRepository.findById(id).orElseThrow(()-> new DataNotFoundException("Checking not found"));
        /**Comprobar que el usuario pueda acceder**/

        return c;
    }

}

