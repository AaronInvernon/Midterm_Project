package com.ironhack.Midterm.Project.service;

import com.ironhack.Midterm.Project.exceptions.DataNotFoundException;
import com.ironhack.Midterm.Project.model.User;
import com.ironhack.Midterm.Project.repository.UserRepository;
import com.ironhack.Midterm.Project.security.CustomSecurityUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    private  final Logger LOGGER = LogManager.getLogger(UserDetailsServiceImpl.class);

    public User findById(Integer id){ return userRepo.findById(id).orElseThrow(()-> new DataNotFoundException("User not found"));}

    public void login(Integer id){
        LOGGER.info("[INIT] - User log in id:" + id);
        User u = findById(id);
        u.login();
        userRepo.save(u);
        LOGGER.info("[END] - User log in id:" + id);
    }

    public void logout(Integer id){
        LOGGER.info("[INIT] - User log out id:" + id);
        User u = findById(id);
        u.logout();
        userRepo.save(u);
        LOGGER.info("[END] - User log out id:" + id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("Invalid username/password combination.");

        return new CustomSecurityUser(user);

    }
}
