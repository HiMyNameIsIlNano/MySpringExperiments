package com.myexperiments.springmvc.configuration;

import domain.service.SpitterRepository;
import domain.service.impl.SpitterUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@Conditional(CustomCondition.class)
public class SecurityConfigCustom extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpitterRepository spitterRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new SpitterUserServiceImpl(spitterRepository));
    }
}
