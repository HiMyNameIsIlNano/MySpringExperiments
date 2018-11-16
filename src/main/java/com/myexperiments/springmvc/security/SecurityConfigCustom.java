package com.myexperiments.springmvc.security;

import com.myexperiments.springmvc.security.condition.CustomCondition;
import com.myexperiments.springmvc.domain.service.SpitterRepository;
import com.myexperiments.springmvc.domain.service.impl.SpitterUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@PropertySource("classpath:beans-config.properties")
@Conditional(CustomCondition.class)
public class SecurityConfigCustom extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpitterRepository spitterRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new SpitterUserServiceImpl(spitterRepository));
    }
}
