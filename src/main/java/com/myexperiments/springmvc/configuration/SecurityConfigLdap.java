package com.myexperiments.springmvc.configuration;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Conditional(LdapCondition.class)
public class SecurityConfigLdap extends WebSecurityConfigurerAdapter {

    /**
     * For an embedded LDAP the Builder can be configured with the following options and specifying from which ldif
     * file the Users have to be picked up:
     *
     * .root("dc=habuma,dc=com")
     * .ldif("classpath:users.ldif")
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userSearchBase("ou=people") // Users are to be sought for where the organization unit is 'people'
                .userSearchFilter("(uid={0})")
                .groupSearchBase("ou=groups") // Groups are to be sought for where the organization unit is 'groups'
                .groupSearchFilter("member={0}")
                .passwordCompare()
                /**
                 * Passwords must be encrypted before being sent to the LDAP. The assumption is, that the password is store in MD5 in LDAP.
                 * */
                .passwordEncoder(new Md5PasswordEncoder())
                .passwordAttribute("passcode")
                .and()
                .contextSource()
                .url("ldap://url.com:xxx/dc=mydomain,dc=com"); // Url of the remote LDAP.
    }

}
