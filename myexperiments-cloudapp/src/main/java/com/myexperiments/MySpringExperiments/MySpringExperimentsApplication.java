package com.myexperiments.MySpringExperiments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* Because the Application will be run from an executable JAR, it is necessary to have a main class that will
* be executed when that JAR file is run.
*
* The @SpringBootApplication is a composite annotation made up from:
* 	1. @SpringBootConfiguration
* 	2. @EnableAutoConfiguration
* 	3. @ComponentScan
**/
@SpringBootApplication
public class MySpringExperimentsApplication {

	public static void main(String[] args) {
		// It performs the actual bootstrapping of the application, creating the Spring application context
		SpringApplication.run(MySpringExperimentsApplication.class, args);
	}

}

