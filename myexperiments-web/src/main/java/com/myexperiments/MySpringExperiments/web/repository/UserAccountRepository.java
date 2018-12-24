package com.myexperiments.MySpringExperiments.web.repository;

import com.myexperiments.MySpringExperiments.web.domain.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    Optional<UserAccount> findByUsername(String username);

}