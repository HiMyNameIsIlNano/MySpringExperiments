package com.myexperiments.springmvc.configuration;

import com.myexperiments.springmvc.domain.service.SpitterRepository;
import com.myexperiments.springmvc.domain.service.impl.SpitterUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:beans-config.properties")
@Conditional(JdbcCondition.class)
public class SecurityConfigJdbc extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SpitterRepository spitterRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new SpitterUserServiceImpl(spitterRepository))
                .and()
                .jdbcAuthentication()
                .dataSource(dataSource)
                /**
                 *   This tells Spring Security that the Users and the Roles that are existing in the application
                 *   are to be sought in other tables.
                 *
                 *   When replacing the default SQL queries with those of your own design, it’s important to adhere
                 *   to the basic contract of the queries.
                */
                .usersByUsernameQuery("select username, password, true from Spitter where username=?")
                .authoritiesByUsernameQuery("select username, 'ROLE_USER' from Spitter where username=?")
                // The password in the database is never decoded
                .passwordEncoder(new BCryptPasswordEncoder(10, SecureRandom.getInstance("53cr3t")));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * This one makes use of the SpEL in order to restrict the access to the application. This combination with the
         * hasRole and hasIpAddress would not be possible with a traditional Spring Security Methods.
         * */
        http.authorizeRequests()
                .antMatchers("/spitter/me")
                .access("hasRole('ROLE_SPITTER') and hasIpAddress('192.168.1.2')");
    }



}
