package com.myexperiments.springmvc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
* Enabled Spring Data’s automatic JPA repository generation from JpaRepository Interface
**/
@Configuration
@EnableJpaRepositories(basePackages="com.myexperiments.springmvc.domain")
public class JpaConfiguration {
}
