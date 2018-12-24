package com.myexperiments.MySpringExperiments;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/*
* @RunWith is a JUnit annotation, providing a test runner that guides JUnit in running a test.
* SpringRunner is an alias for SpringJUnit4ClassRunner.
* @SpringBootTest tells JUnit to bootstrap the test with Spring Boot capabilities.
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringExperimentsApplicationTests {

	@Test
	public void contextLoads() {
	}

}

