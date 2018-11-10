package com.myexperiments.springmvc.configuration;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
// @EnableWebMvcSecurity this is deprecated and therefore the EnableWebSecurity will be used.
@Conditional(InMemoryCondition.class)
public class SecurityConfigInMemory extends WebSecurityConfigurerAdapter {

    /**
     * Spring Security is extremely flexible and is capable of authenticating users against virtually any service store.
     *
     * In this case the Data Store is an in-memory DB.
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                // the roles() method is a shortcut for the authorities() method
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
    }

    /**
     * One can chain antMatchers and anyRequest at his own like, but they’ll be applied in the order given. For that
     * reason, it is important to configure the most specific request path patterns first and the least specific ones
     * (such as anyRequest() ) last.
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/home")
                .permitAll();
    }

}
