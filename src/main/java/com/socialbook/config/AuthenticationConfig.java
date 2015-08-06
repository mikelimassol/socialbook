package com.socialbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.socialbook.auth.components.RestAuthenticationEntryPoint;
import com.socialbook.auth.handler.CustomSavedRequestAwareAuthenticationSuccessHandler;
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
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private CustomSavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler;

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
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/auth/**").authenticated()
                .antMatchers("/protected/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .rememberMe()
                .tokenValiditySeconds(TOKEN_VALIDITY_SECONDS).key("socialbookkey")
                .and()
                .logout();
    }

    @Bean
    public CustomSavedRequestAwareAuthenticationSuccessHandler mySuccessHandler() {
        return new CustomSavedRequestAwareAuthenticationSuccessHandler();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

}