package com.myexperiments.springmvc.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * This filter will intercept requests coming into the application and delegate them to
 * a bean whose ID is springSecurityFilter Chain.
 * */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {

}
