package com.myexperiments.MySpringExperiments.web.config;

import com.myexperiments.MySpringExperiments.web.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
// The following Syntax would work all the same @Profile({"!IN_MEMORY", "!LDAP"})
@ConditionalOnProperty(name = "pizza.security.config", havingValue = "JDBC")
public class JdbcSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private UserAccountService userAccountService;

    /**
     * Any calls to encoder() will then be intercepted to return the bean instance from the application context
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public JdbcSecurityConfig(DataSource dataSource, UserAccountService userAccountService) {
        this.dataSource = dataSource;
        this.userAccountService = userAccountService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAccountService)
                .passwordEncoder(passwordEncoder())
                .and()
                    .jdbcAuthentication()
                    .dataSource(dataSource);

        /*
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                    "select username, password, enabled from UserAccount " +
                            "where username=?")
                .authoritiesByUsernameQuery(
                    "select username, authority from UserAuthorities " +
                            "where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());
        */
    }

    /**
     * Security rules declared first take precedence over those declared lower down. In this case, switching the antMatchers
     * rules would make the complete application accessible without any login.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()/*
                .antMatchers("/design", "/orders")
                .hasRole("USER") // Role should not start with 'ROLE_' since it is automatically prepended by Spring Security.
                .antMatchers("/", "/**")
                .permitAll()*/
                .antMatchers("/design", "/orders")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**").access("permitAll")
            .and() // The previous section is finished and here a new one starts. THe order in this case is not relevant.
                .formLogin()
                /*
                * By default, Spring Security listens for login requests at /login and expects that the username and password fields
                * be named username and password. It is however possible to override this behaviour (see below).
                */
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .usernameParameter("username") // If the usernamne Field is name differently in the login page, it can be redefined here.
                .passwordParameter("password")// If the password Field is name differently in the login page, it can be redefined here.
            .and()
                .logout()
                .logoutSuccessUrl("/")// Make H2-Console non-secured; for debug purposes
            .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**")
            .and()
                .headers()
                .frameOptions() // Allow pages to be loaded in frames from the same origin; needed for H2-Console
                .sameOrigin();
    }

}
