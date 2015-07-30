package com.socialbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import com.socialbook.service.impl.AuthenticationUserServiceImpl;

/**
 * 
 * @author Michael Papamichael
 * 
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {
    
    private static final Integer TOKEN_VALIDITY_SECONDS = 2419200;

    @Autowired
    private AuthenticationUserServiceImpl sucurityUserService;
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(sucurityUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http//the reason i disabled this is because i had problem with ajax requests
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/profile/**").authenticated().and().formLogin();
        
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}