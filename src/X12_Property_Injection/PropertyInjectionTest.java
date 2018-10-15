package X12_Property_Injection;

import X12_Property_Injection.config.PropertyInjectionConfig;
import X12_Property_Injection.model.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PropertyInjectionConfig.class)
public class PropertyInjectionTest {

    @Autowired
    private Auto auto;

    @Test
    public void testContext(){
        assertNotNull(auto);
        assertNotNull(auto.accelerate());
        assertTrue(auto.accelerate().contains("Ferrari"));
    }
}
