package com.myexperiments.springmvc.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;

/**
 * This filter will intercept requests coming into the application and delegate them to
 * a bean whose ID is springSecurityFilter Chain.
 * */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {

    /**
     * Placing MultipartFilter before Spring Security:
     *
     * https://stackoverflow.com/questions/21397939/spring-security-3-2-csrf-support-for-multipart-requests
     *
     * The first option is to ensure that the MultipartFilter is specified before the Spring Security filter.
     * Specifying the MultipartFilter after the Spring Security filter means that there is no authorization for
     * invoking the MultipartFilter which means anyone can place temporary files on your server.  However, only
     * authorized users will be able to submit a File that is processed by your application. In general, this
     * is the recommended approach because the temporary file upload should have a negligble impact on most servers.
     * */
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        insertFilters(servletContext, new MultipartFilter());
    }

}
