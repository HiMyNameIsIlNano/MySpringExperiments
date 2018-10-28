package com.myexperiments.springmvc.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
* This DispatcherServlet will be automatically discovered when deployed in a Servlet 3.0 container and
* be used to configure the servlet context.
*
* The only gotcha with configuring DispatcherServlet in this way, as opposed to in a web.xml file, is
* that it will only work when deploying to a server that supports Servlet 3.0, such as Apache Tomcat 7
* or higher. The Servlet 3.0 specification has been final since December 2009, and the odds are good that
* you’ll be deploying your applications to a servlet container that supports Servlet 3.0.
**/
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
    * This one identifies one or more paths that DispatcherServlet will be mapped to.
    * In this case, it’s mapped to /, indicating that it will be the application’s default servlet.
    * It will handle all requests coming into the application.
    **/
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    /*
    * Application Context Configuration (e.g. Back-End Beans)
    * */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }

    /*
    * Web Application Configuration (e.g. Controllers and Views)
    * */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }
}