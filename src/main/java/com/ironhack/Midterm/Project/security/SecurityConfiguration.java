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

/** ROLE_THIRD_PARTY, ROLE_ACCOUNT_HOLDER, ROLE_ADMIN
 *
 * /account/{id} --GET
 * /account/{id}/balance --GET
 * /account/{id}/credit --POST
 * /account/{id}/debit --POST
 * /account/{id}/transference --POST
 *
 * /user/accountHolder  --POST
 * /user/admin          --POST
 * /user/thirdParty     --POST
 *
 * /account/checking/{id}  --POST
 * /account/creditCard     --POST
 * /account/saving         --POST
 *
 * /user/{id}/login --POST
 * /user/{id}/logout --POST
 * **/
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

        httpSecurity.authorizeRequests()
                /*.mvcMatchers(HttpMethod.POST, "/user/accountHolder").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST, "/user/admin").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST, "/user/thirdParty").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.GET, "/account/{id}").hasAnyAuthority("ROLE_ADMIN", "ROLE_ACCOUNT_HOLDER")
                .mvcMatchers(HttpMethod.GET, "/account/{id}/balance").hasAnyAuthority("ROLE_ADMIN", "ROLE_ACCOUNT_HOLDER")
                .mvcMatchers(HttpMethod.POST, "/account/{id}/credit").hasAnyAuthority("ROLE_ADMIN", "ROLE_ACCOUNT_HOLDER", "ROLE_THIRD_PARTY")
                .mvcMatchers(HttpMethod.POST, "/account/{id}/debit").hasAnyAuthority("ROLE_ADMIN", "ROLE_ACCOUNT_HOLDER", "ROLE_THIRD_PARTY")
                .mvcMatchers(HttpMethod.POST, "/account/{id}/transference").hasAnyAuthority("ROLE_ADMIN", "ROLE_ACCOUNT_HOLDER")
                .mvcMatchers(HttpMethod.POST, "/account/checking/{id}").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST, "/account/creditCard").hasAuthority("ROLE_ADMIN")
                .mvcMatchers(HttpMethod.POST, "/account/saving").hasAuthority("ROLE_ADMIN")*/
                .anyRequest().permitAll();

    }
}
