package CH1_XML_Config.soundsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:CH1_XML_Config/config/beans-definition.xml" })
public class CDPlayerTest {

    @Autowired
    private CompactDisc compactDisc;

    @Test
    public void compactDiscShouldNotBeNull() {
        assertNotNull(compactDisc);
    }

}
