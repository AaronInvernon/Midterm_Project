package com.ironhack.Midterm.Project.security;

import com.ironhack.Midterm.Project.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.csrf().disable();

        httpSecurity.headers().frameOptions().disable();

        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/salesrep/save/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET, "/salerep/find_by_id/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET, "/salesreps").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET, "/leads/find_by_salesrep").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET, "/lead/convert**").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll();

    }
}
