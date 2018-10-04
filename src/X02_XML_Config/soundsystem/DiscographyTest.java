package X02_XML_Config.soundsystem;

import X02_XML_Config.soundsystem.model.Discography;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:X02_XML_Config/config/beans-definition.xml"})
public class DiscographyTest {

    @Autowired
    private Discography beatlesDiscography;

    @Test
    public void discographyShouldNotBeNull() {
        assertNotNull(beatlesDiscography);
    }

    @Test
    public void discographyIsNotEmpty() {
        assertNotNull(beatlesDiscography);
        assertEquals(2, beatlesDiscography.getDiscography().size());
        System.out.println(beatlesDiscography.getDiscography().toString());
    }

}
