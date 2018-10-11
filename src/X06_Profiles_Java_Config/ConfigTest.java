package X06_Profiles_Java_Config;

import X06_Profiles_Java_Config.config.DevelopmentProfileConfig;
import X06_Profiles_Java_Config.soundsystem.model.Shape;
import X06_Profiles_Java_Config.soundsystem.ShapeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "Square")
@ContextConfiguration(classes = DevelopmentProfileConfig.class)
public class ConfigTest {

    @Autowired
    private Shape shape;

    @Test
    public void testContext(){
        assertNotNull(shape);
        assertEquals(ShapeEnum.SQUARE, shape.whoAmI());
    }

}