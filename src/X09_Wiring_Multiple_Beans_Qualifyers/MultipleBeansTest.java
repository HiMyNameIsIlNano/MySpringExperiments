package X09_Wiring_Multiple_Beans_Qualifyers;

import X09_Wiring_Multiple_Beans_Qualifyers.config.MultipleBeansConfig;
import X09_Wiring_Multiple_Beans_Qualifyers.dessert.ShapeEnum;
import X09_Wiring_Multiple_Beans_Qualifyers.dessert.model.Dessert;
import X09_Wiring_Multiple_Beans_Qualifyers.dessert.model.Shape;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MultipleBeansConfig.class)
public class MultipleBeansTest {

    @Autowired
    private Dessert dessert;

    @Autowired
    @Qualifier("rounded")
    private Shape shape;

    @Test
    public void doTestPrimaryAnnotationWorks() {
        assertNotNull(dessert);
    }

    @Test
    public void doTestQualifierAnnotationWorks() {
        assertNotNull(shape);
        assertEquals(ShapeEnum.CIRCLE, shape.whoAmI());
    }
}

