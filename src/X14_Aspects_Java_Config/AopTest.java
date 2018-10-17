package X14_Aspects_Java_Config;

import X14_Aspects_Java_Config.config.AspectsConfig;
import X14_Aspects_Java_Config.service.DummyService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AspectsConfig.class)
public class AopTest {

    @Rule
    public SystemOutResource sysOut = new SystemOutResource();

    @Autowired
    private DummyService service;

    @Test
    public void serviceIsNotNull() {
        assertNotNull(service);
        service.aMethodIWantToTest("I am alive");
        assertThat(sysOut.asString(), containsString("I was executed before calling the method"));
        assertThat(sysOut.asString(), containsString("I was executed after calling the method"));
        assertThat(sysOut.asString(), containsString("I was executed before around calling the method"));
        assertThat(sysOut.asString(), containsString("I was executed after around calling the method"));
    }
}


