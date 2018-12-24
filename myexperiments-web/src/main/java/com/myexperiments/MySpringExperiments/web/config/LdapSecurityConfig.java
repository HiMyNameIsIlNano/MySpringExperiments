package com.myexperiments.MySpringExperiments.web.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(name = "pizza.security.config", havingValue = "LDAP")
public class LdapSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * This example specifies that users be searched for where the organizational unit is people.
     * Groups should be searched for where the organizational unit is groups.
     *
     * The default strategy for authenticating against LDAP is to perform a bind operation,
     * authenticating the user directly to the LDAP server. Because the comparison is done within the LDAP server,
     * the actual password remains secret.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("(uid={0})")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
                .passwordCompare() // Do a server-side password comparison
                .passwordEncoder(new BCryptPasswordEncoder()) // The password is passed Encrypted to the LDAP server
                .passwordAttribute("passcode") // the password given in the login form will be compared with the value of the "passcode" attribute in the user’s LDAP entry.
                .and()
                    .contextSource()
                    .root("dc=pizzacloud,dc=com") // Embedded LDAP
                    .ldif("classpath:users.ldif"); // LDAP File with Users
                    // .url("ldap://pizzacloud.com:389/dc=pizzacloud,dc=com"); // By default, Spring Security’s LDAP authentication assumes that the LDAP server is listening on port 33389 on localhost
    }

}
