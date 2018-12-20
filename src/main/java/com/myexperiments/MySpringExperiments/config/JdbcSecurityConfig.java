package com.myexperiments.MySpringExperiments.config;

import com.myexperiments.MySpringExperiments.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Profile(value = "JDBC")
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/design", "/orders")
                .hasRole("ROLE_USER")
                .antMatchers("/", "/**").permitAll()
        ;
    }

}
