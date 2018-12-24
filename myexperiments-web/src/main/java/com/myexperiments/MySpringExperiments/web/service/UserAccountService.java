package com.myexperiments.MySpringExperiments.web.service;

import com.myexperiments.MySpringExperiments.web.domain.UserAccount;
import com.myexperiments.MySpringExperiments.web.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService implements UserDetailsService {

    private UserAccountRepository accountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * The loadByUsername() method has one simple rule: it must never return null.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> userAccount = accountRepository.findByUsername(username);
        return userAccount
                .orElseThrow(() -> new UsernameNotFoundException("User: '" + username + "' Not Found"));
    }
}
