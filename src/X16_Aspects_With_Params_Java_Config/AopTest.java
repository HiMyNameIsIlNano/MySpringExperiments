package X16_Aspects_With_Params_Java_Config;

import X16_Aspects_With_Params_Java_Config.aspect.MessageTypeEnum;
import X16_Aspects_With_Params_Java_Config.aspect.MyAspect;
import X16_Aspects_With_Params_Java_Config.config.AspectsConfig;
import X16_Aspects_With_Params_Java_Config.service.DummyService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AspectsConfig.class)
public class AopTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Autowired
    private DummyService service;

    @Autowired
    private MyAspect aspect;

    @Test
    public void serviceIsNotNull() {
        assertNotNull(service);

        service.aMethodIWantToTest(MessageTypeEnum.INFO);
        service.aMethodIWantToTest(MessageTypeEnum.INFO);
        service.aMethodIWantToTest(MessageTypeEnum.INFO);

        service.aMethodIWantToTest(MessageTypeEnum.WARN);

        service.aMethodIWantToTest(MessageTypeEnum.ERROR);
        service.aMethodIWantToTest(MessageTypeEnum.ERROR);

        assertEquals(aspect.getMessageCount(MessageTypeEnum.INFO), 3);
        assertEquals(aspect.getMessageCount(MessageTypeEnum.WARN), 1);
        assertEquals(aspect.getMessageCount(MessageTypeEnum.ERROR), 2);
    }
}


