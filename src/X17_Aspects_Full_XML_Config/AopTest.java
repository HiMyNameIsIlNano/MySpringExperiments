package X17_Aspects_Full_XML_Config;

import X17_Aspects_Full_XML_Config.service.DummyService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:X17_Aspects_Full_XML_Config/config/beans-definition.xml")
public class AopTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Autowired
    private DummyService service;

    @Test
    public void serviceIsNotNull() {
        assertNotNull(service);
        service.aMethodIWantToTest("I am alive");
        service.aMethodIWantToImprove("I want to get better");

        String log = systemOutRule.getLog();
        assertNotNull(log);

        assertTrue(log.contains("I was executed before calling the method"));
        assertTrue(log.contains("I was executed after calling the method"));
        assertTrue(log.contains("I was executed before around calling the method"));
        assertTrue(log.contains("I was executed after around calling the method"));
    }

}


