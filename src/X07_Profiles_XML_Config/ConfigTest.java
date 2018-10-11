package X07_Profiles_XML_Config;

import X07_Profiles_XML_Config.soundsystem.ShapeEnum;
import X07_Profiles_XML_Config.soundsystem.model.Shape;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "Rectangle")
@ContextConfiguration(locations = {"classpath:X07_Profiles_XML_Config/config/beans-definition.xml"})
public class ConfigTest {

    @Autowired
    private Shape shape;

    @Test
    public void testContext(){
        assertNotNull(shape);
        assertEquals(ShapeEnum.RECTANGLE, shape.whoAmI());
    }

}