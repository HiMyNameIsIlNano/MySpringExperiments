package X13_Value_Injection_And_SpEL;

import X13_Value_Injection_And_SpEL.configuration.CDPlayerExplicitConfig;
import X13_Value_Injection_And_SpEL.soundsystem.model.CompactDisc;
import X13_Value_Injection_And_SpEL.soundsystem.model.MediaPlayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerExplicitConfig.class)
public class CDPlayerExplicitConfigTest {

    @Autowired
    private CompactDisc compactDisc;

    @Test
    public void compactDiscShouldNotBeNull() {
        assertNotNull(compactDisc);
        assertTrue(compactDisc.toString().contains("White Album"));
    }

}
