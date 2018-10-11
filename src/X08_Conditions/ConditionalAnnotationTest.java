package X08_Conditions;

import X08_Conditions.config.ConditionsConfig;
import X08_Conditions.model.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConditionsConfig.class)
@TestPropertySource(properties="auto.type=Ferrari")
public class ConditionalAnnotationTest {

    @Autowired
    private Auto auto;

    @Test
    public void testContext(){
        assertNotNull(auto);
        assertNotNull(auto.accelerate());
        assertTrue(auto.accelerate().contains("Ferrari"));
    }
}
