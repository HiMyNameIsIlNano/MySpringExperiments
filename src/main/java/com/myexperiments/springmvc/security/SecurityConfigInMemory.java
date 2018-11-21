package com.myexperiments.springmvc.security;

import com.myexperiments.springmvc.security.condition.InMemoryCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:beans-config.properties")
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
        http.csrf()
            .ignoringAntMatchers("/console/**") // This is needed to access the H2 Console
            .and()
                .headers()
                .frameOptions()
                .sameOrigin()
            .and()
                .formLogin()
                .loginPage("/login") // It enables the default login page
                .successForwardUrl("/home")
            .and()
                /*
                * The following configuration demonstrates how to allow token based remember me authentication.
                * Upon authentication if the HTTP parameter named "remember-me" is sent, the user will be remembered
                * for a period of time given by the validityInSeconds parameter.
                * */
                .rememberMe()
                .tokenValiditySeconds(2419200) // Valid for 4 Weeks
                .key("spittrKey") // The password used to has the User's password into a cookie
            .and()
                .logout()
                .logoutSuccessUrl("/")
                .logoutUrl("/signout")
            .and()
                .authorizeRequests()
                    .antMatchers("/spitters/me").hasRole("SPITTER") // /spitters/me can only be accessed by Users with role SPITTER. The ROLE_ prefix is automatically prepended.
                    .antMatchers(HttpMethod.POST, "/spittles").authenticated() // POST to /spittles must be authenticated
                    .anyRequest().permitAll()
            .and()
                .requiresChannel() // Use HTTPS
                .antMatchers("/spitter/form")
                .requiresSecure(); // all the other request are not authenticated
    }

}
